import type {
  FetchGameStart,
  FetchRoomIndex,
  FinishRoom,
  GameStartStatus,
  RoomIndexResponseBody,
} from './type';
import { isGameStartStatus, isRoomIndexResponseBody } from './type';
import { InvalidResponseBodyError } from '@/features/error';
import { JINROH_API_BASE_URL } from '@/url';

export const fetchRoomIndex: FetchRoomIndex = async () => {
  const res = await fetch(JINROH_API_BASE_URL + '/room-index', {
    method: 'GET',
    credentials: 'include',
  });
  const roomIndexResponseBody = (await res.json()) as RoomIndexResponseBody;

  if (!isRoomIndexResponseBody(roomIndexResponseBody)) {
    throw new InvalidResponseBodyError(
      `responseBody is not in the expected format. body: ${JSON.stringify(
        roomIndexResponseBody,
      )}`,
    );
  }

  return roomIndexResponseBody;
};

export const fetchGameStart: FetchGameStart = async () => {
  const res = await fetch(JINROH_API_BASE_URL + '/game-start', {
    method: 'GET',
    credentials: 'include',
  });

  const gameStartStatus = (await res.text()) as GameStartStatus;

  if (!isGameStartStatus(gameStartStatus)) {
    throw new InvalidResponseBodyError(
      `responseBody is not in the expected format. body: ${JSON.stringify(
        gameStartStatus,
      )}`,
    );
  }

  return gameStartStatus;
};

export const finishRoom: FinishRoom = async () => {
  const res = await fetch(JINROH_API_BASE_URL + '/room-finish', {
    method: 'GET',
    credentials: 'include',
  });

  if (res.status !== 200) {
    throw new Error('Failed to finish room');
  }
};
