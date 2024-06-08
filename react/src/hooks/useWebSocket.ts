import { useEffect } from 'react';
import SockJS from 'sockjs-client/dist/sockjs';
import Stomp from 'webstomp-client';
import type { Subscribe } from '@/type';
import { JINROH_API_BASE_URL } from '@/url';

export const useWebSocket = (callbacks: Subscribe[]): void => {
  useEffect(() => {
    const socket = new SockJS(JINROH_API_BASE_URL + '/jinroh-websocket');
    const options = {debug: true, protocols: Stomp.VERSIONS.supportedProtocols() };
    const stompClient = Stomp.over(socket, options);

    stompClient.connect({}, () => {
      callbacks.forEach((callback) => {
        stompClient.subscribe(callback.path, callback.callback);
      });
    });

    socket.onclose = () => {
      if (confirm("サーバとの接続が切れました。再接続しますか？")) {
        location.reload()
      }
    };

    return () => {
      if (stompClient.connected) {
        stompClient.disconnect();
        socket.close();
      }
    };
  }, [callbacks]);
};
