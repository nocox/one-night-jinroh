import { isJoinedRoomStatus } from './type';
import type { JoinRoom } from './type';
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
