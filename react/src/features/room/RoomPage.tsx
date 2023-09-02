import { useEffect, useState } from 'react';
import { RoomTemplate } from './RoomTemplate';
import { fetchRoomIndex } from './api';
import type { RoomIndexResponseBody } from './type';

export const RoomPage: React.FC = () => {
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
        if (response === null) return;
        setRoomIndexResponseBody(response);
      } catch (error) {
        console.log(error); // TODO: ErrorFallbackを実装する
      }
    };

    void fetchData();
  }, []);

  return (
    <>
      <RoomTemplate roomIndexResponseBody={roomIndexResponseBody} />
    </>
  );
};
