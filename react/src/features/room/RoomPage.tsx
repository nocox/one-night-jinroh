import { useState } from 'react';
import { RoomTemplate } from './RoomTemplate';
import { useRoomData } from './hooks';
import { isGameInfo } from './type';
import type { GameInfo } from './type';
import { InvalidResponseBodyError } from '@/error';
import { useWebSocket } from '@/hooks';
import type { Subscribe } from '@/type';

export const RoomPage: React.FC = () => {
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

      gameInfo.roleList.sort((a, b) => a.roleId - b.roleId);
      setGameInfo(gameInfo);
      console.log(gameInfo);
      alert('ゲームを開始します');

      // TODO: 夜の行動ページを実装し、そこにリダイレクトさせる処理を実装する
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

  console.log(gameInfo);

  useWebSocket([subscribeGameStart, subscribeFinishRoom]);

  return (
    <>
      <RoomTemplate roomIndexResponseBody={roomIndexResponseBody} />
    </>
  );
};
