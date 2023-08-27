import { isJoinedRoomStatus } from './type';
import type { CreateRoom, JoinRoom } from './type';
import { InvalidResponseBodyError } from '@/error';
import { JINROH_API_BASE_URL } from '@/url';

export const joinRoom: JoinRoom = async (dto) => {
  const { roomId } = dto;

  const res = await fetch(JINROH_API_BASE_URL + '/join-room?uuid=' + roomId, {
    method: 'GET',
    credentials: 'include',
  });

  const status = await res.text();

  if (!isJoinedRoomStatus(status)) {
    throw new InvalidResponseBodyError();
  }

  return status;
};

export const createRoom: CreateRoom = async () => {
  const res = await fetch(JINROH_API_BASE_URL + '/create-room', {
    method: 'GET',
    credentials: 'include',
  });

  if (res.status !== 200) {
    throw new Error('CREATE_ROOM_FAILED');
  }
};
