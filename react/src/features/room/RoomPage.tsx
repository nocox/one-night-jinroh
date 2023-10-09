import { useState } from 'react';
import { RoomTemplate } from './RoomTemplate';
import { GameStartModal } from './components/GameStartModal';
import { useRoomData } from './hooks';
import { fetchGameRuleList } from '@/api';

import { useWebSocket } from '@/hooks';
import { useModal } from '@/hooks/useModal';
import type { GameRuleList, Subscribe } from '@/type';

export const RoomPage: React.FC = () => {
  const { open, onOpenModal } = useModal();
  const onCloseModal = () => {
    window.location.href = '/night';
  };

  const [gameRuleList, setGameRuleList] = useState<GameRuleList | undefined>(
    undefined,
  );

  const roomIndexResponseBody = useRoomData();

  const { uuid, hostFlg } = roomIndexResponseBody;

  const subscribeGameStart: Subscribe = {
    path: `/topic/${uuid}`,
    callback: async () => {
      const gameRuleList = await fetchGameRuleList();
      setGameRuleList(gameRuleList);
      onOpenModal();
    },
  };

  const subscribeFinishRoom: Subscribe = {
    path: `/topic/receive-finish-room/${uuid}`,
    callback: () => {
      if (!hostFlg) {
        window.alert('ホストがルームを解散しました');
      }

      window.location.href = '/';
    },
  };

  useWebSocket([subscribeGameStart, subscribeFinishRoom]);

  return (
    <>
      <RoomTemplate roomIndexResponseBody={roomIndexResponseBody} />
      {gameRuleList !== undefined && (
        <GameStartModal
          open={open}
          onCloseModal={onCloseModal}
          gameRuleList={gameRuleList}
        />
      )}
    </>
  );
};
