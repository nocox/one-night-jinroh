import { useState } from 'react';
import { RoomTemplate } from './RoomTemplate';
import { GameStartModal } from './components/GameStartModal';
import { useRoomData } from './hooks';
import { isGameInfo } from './type';
import type { GameInfo } from './type';
import { InvalidResponseBodyError } from '@/error';
import { useWebSocket } from '@/hooks';
import { useModal } from '@/hooks/useModal';
import type { Subscribe } from '@/type';

export const RoomPage: React.FC = () => {
  const { open, onOpenModal } = useModal();
  const onCloseModal = () => {
    window.location.href = '/night';
  };

  const [gameInfo, setGameInfo] = useState<GameInfo | undefined>(undefined);

  const roomIndexResponseBody = useRoomData();

  const { uuid, hostFlg } = roomIndexResponseBody;

  const subscribeGameStart: Subscribe = {
    path: `/topic/${uuid}`,
    callback: (message) => {
      if (message === undefined) {
        throw new InvalidResponseBodyError(
          `Invalid response body: ${JSON.stringify(message)}`,
        );
      }

      const gameInfo = message.body
        ? (JSON.parse(message.body) as unknown)
        : undefined;

      if (!isGameInfo(gameInfo)) {
        throw new InvalidResponseBodyError(
          `Invalid response body: ${JSON.stringify(gameInfo)}`,
        );
      }

      setGameInfo(gameInfo);
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
      {gameInfo !== undefined && (
        <GameStartModal
          open={open}
          onCloseModal={onCloseModal}
          gameInfo={gameInfo}
        />
      )}
    </>
  );
};
