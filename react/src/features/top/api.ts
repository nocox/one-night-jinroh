import type {
  CreateRoom,
  CreateRoomStatus,
  JoinRoom,
  JoinedRoomStatus,
} from './type';
import { JINROH_API_BASE_URL } from '@/url';

export const joinRoom: JoinRoom = async (dto) => {
  const { roomId } = dto;

  const res = await fetch(JINROH_API_BASE_URL + '/join-room?uuid=' + roomId, {
    method: 'GET',
    credentials: 'include',
  });

  return (await res.text()) as JoinedRoomStatus;
};

export const createRoom: CreateRoom = async () => {
  const res = await fetch(JINROH_API_BASE_URL + '/create-room', {
    method: 'GET',
    credentials: 'include',
  });

  return (await res.text()) as CreateRoomStatus;
};
