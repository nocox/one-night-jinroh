import { useEffect, useState } from 'react';
import { fetchRoomIndex } from '../api';
import type { RoomIndexResponseBody } from '../type';

export const useRoomData = (): RoomIndexResponseBody => {
  const [roomIndexResponseBody, setRoomIndexResponseBody] =
    useState<RoomIndexResponseBody>({
      uuid: '',
      userList: [],
      hostFlg: false,
      myselfUserId: -1,
    });

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetchRoomIndex();
        setRoomIndexResponseBody(response);
        console.log('koko');
      } catch (error) {
        console.log(error); // TODO: ErrorFallbackを実装する
      }
    };

    void fetchData();
  }, []);

  return roomIndexResponseBody;
};
