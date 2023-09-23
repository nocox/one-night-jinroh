import useSWR from 'swr';

import { fetchRoomIndex } from '../api';
import type { RoomIndexResponseBody } from '../type';

export const useRoomData = (): {
  roomIndexResponseBody: RoomIndexResponseBody | undefined;
} => {
  const { data, error } = useSWR<RoomIndexResponseBody, Error>(
    'room-index',
    fetchRoomIndex,
  );

  if (error) {
    throw error;
  }

  return {
    roomIndexResponseBody: data,
  };
};
