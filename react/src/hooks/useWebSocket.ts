import { useEffect } from 'react';
import SockJS from 'sockjs-client/dist/sockjs';
import Stomp from 'webstomp-client';
import type { Subscribe } from '@/type';
import { JINROH_API_BASE_URL } from '@/url';

export const useWebSocket = (callbacks: Subscribe[]): void => {
  useEffect(() => {
    const socket = new SockJS(JINROH_API_BASE_URL + '/jinroh-websocket');
    const stompClient = Stomp.over(socket);

    stompClient.connect({}, () => {
      callbacks.forEach((callback) => {
        stompClient.subscribe(callback.path, callback.callback);
      });
    });

    return () => {
      if (stompClient.connected) {
        stompClient.disconnect();
      }
      socket.close();
    };
  }, [callbacks]);
};
