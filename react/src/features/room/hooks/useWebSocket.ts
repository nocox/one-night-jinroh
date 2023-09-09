import { useEffect, useState } from 'react';
import SockJS from 'sockjs-client/dist/sockjs';
import Stomp from 'webstomp-client';
import type { GameInfo } from '../type';
import { isGameInfo } from '../type';
import { InvalidResponseBodyError } from '@/error';
import { JINROH_API_BASE_URL } from '@/url';

export const useWebSocket = (uuid: string, hostFlg: boolean): void => {
  const [gameInfo, setGameInfo] = useState<GameInfo | undefined>(undefined);
  console.log(gameInfo);

  useEffect(() => {
    const socket = new SockJS(JINROH_API_BASE_URL + '/jinroh-websocket');
    const stompClient = Stomp.over(socket);

    stompClient.connect({}, () => {
      stompClient.subscribe(`/topic/${uuid}`, (message) => {
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

        // TODO: 夜の行動ページを実装し、そこにリダイレクトさせる処理を実装する
      });

      stompClient.subscribe('/topic/receive-finish-room/' + uuid, () => {
        if (!hostFlg) {
          window.alert('ホストがルームを解散しました');
        }

        window.location.href = '/';
      });
    });

    return () => {
      if (stompClient.connected) {
        stompClient.disconnect();
      }
      socket.close();
    };
  }, [uuid, hostFlg]);
};
