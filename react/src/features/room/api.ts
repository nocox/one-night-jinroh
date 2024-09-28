import type {
  ExitRoom,
  FetchGameStart,
  FetchRoomIndex,
  FinishRoom,
  FinishRoomStatus,
  GameStartStatus,
  RoomIndexResponseBody,
} from './type';

import { JINROH_API_BASE_URL } from '@/url';

export const fetchRoomIndex: FetchRoomIndex = async () => {
  const res = await fetch(JINROH_API_BASE_URL + '/room-index', {
    method: 'GET',
    credentials: 'include',
  });

  return (await res.json()) as RoomIndexResponseBody;
};

export const fetchGameStart: FetchGameStart = async () => {
  const res = await fetch(JINROH_API_BASE_URL + '/game-start', {
    method: 'GET',
    credentials: 'include',
  });

  return (await res.text()) as GameStartStatus;
};

export const finishRoom: FinishRoom = async () => {
  const res = await fetch(JINROH_API_BASE_URL + '/room-finish', {
    method: 'GET',
    credentials: 'include',
  });

  return (await res.text()) as FinishRoomStatus;
};

export const exitRoom: ExitRoom = async () => {
  const res = await fetch(JINROH_API_BASE_URL + '/exit-room', {
    method: 'POST',
    credentials: 'include',
  });

  if (!res.ok) {
    throw new Error('Failed to exit room');
  }
};
