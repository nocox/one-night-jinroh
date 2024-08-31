import {FetchJoinedRoom, isCreateRoomStatus, isFetchJoinedRoomStatus, isJoinedRoomStatus} from './type';
import type { CreateRoom, JoinRoom } from './type';
import { InvalidResponseBodyError } from '@/features/error';
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

  const status = await res.text();

  if (!isCreateRoomStatus(status)) {
    throw new InvalidResponseBodyError();
  }

  return status
};

export const fetchJoinedRoom: FetchJoinedRoom = async () => {

  const res = await fetch(JINROH_API_BASE_URL + '/joined-room', {
    method: 'GET',
    credentials: 'include',
  });

  const status = await res.text();

  if (!isFetchJoinedRoomStatus(status)) {
    throw new InvalidResponseBodyError();
  }

  return status;
};
