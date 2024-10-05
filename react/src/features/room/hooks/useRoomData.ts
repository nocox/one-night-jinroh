import { useEffect, useState } from 'react';
import type { RoomIndexResponseBody } from '../type';
import { fetchGetWrapper } from '@/api';

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
        const res = await fetchGetWrapper<RoomIndexResponseBody>('/room-index');
        setRoomIndexResponseBody(res);
      } catch (err) {
        console.log(err);
      }
    };

    void fetchData();
  }, []);

  return roomIndexResponseBody;
};
