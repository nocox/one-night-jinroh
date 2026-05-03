import type React from 'react';
import { useState } from 'react';
import { RoomTemplate } from './RoomTemplate';
import { GameStartModal } from './components/GameStartModal';
import { useRoomData } from './hooks';
import type { GameInfo } from './type';
import { Loading } from '@/components';
import { InvalidResponseBodyError } from '@/features/error';
import { exitRoom } from '@/features/room/api';
import { useWebSocket } from '@/hooks';
import { useModal } from '@/hooks/useModal';
import type { Subscribe } from '@/type';

export const RoomPage: React.FC = () => {
  const { open, onOpenModal } = useModal(false);
  const onCloseModal = () => {
    window.location.href = '/night';
  };

  const [gameInfo, setGameInfo] = useState<GameInfo | undefined>(undefined);
  const { uuid, userList, hostFlg, myselfUserId, isLoading } = useRoomData();

  const subscribeGameStart: Subscribe = {
    path: `/topic/${uuid ?? ''}`,
    callback: (message) => {
      if (message === undefined) {
        throw new InvalidResponseBodyError(
          `Invalid response body: ${JSON.stringify(message)}`,
        );
      }

      const gameInfo = message.body
        ? (JSON.parse(message.body) as GameInfo)
        : undefined;

      setGameInfo(gameInfo);
      onOpenModal();
    },
  };

  const subscribeFinishRoom: Subscribe = {
    path: `/topic/receive-finish-room/${uuid ?? ''}`,
    callback: async () => {
      window.alert('ルームが解散されました');
      await exitRoom();
      window.location.href = '/';
    },
  };

  useWebSocket(
    uuid !== undefined ? [subscribeGameStart, subscribeFinishRoom] : [],
  );

  if (
    uuid === undefined ||
    userList === undefined ||
    hostFlg === undefined ||
    myselfUserId === undefined ||
    isLoading
  ) {
    return <Loading />;
  }

  return (
    <>
      <RoomTemplate
        uuid={uuid}
        userList={userList}
        hostFlg={hostFlg}
        myselfUserId={myselfUserId}
      />
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
