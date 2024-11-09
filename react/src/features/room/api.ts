import type {ExitRoom, FinishRoom, FinishRoomStatusResponse} from './type';
import { fetchGetWrapper, fetchPostWrapper } from '@/api';

export const joinGame = async (dto: {gameId: number}): Promise<string> => {
  const {gameId} = dto
  const res = await fetch(JINROH_API_BASE_URL + '/join-game' + '?gameId=' + gameId.toString(), {
    method: 'POST',
    credentials: 'include',
    headers: {
      'Content-Type': 'application/json',
    },
  });

  if (!res.ok) {
    throw new Error('Failed to join game');
  }

  return "JOIN_GAME_SUCCESS"
}

export const finishRoom: FinishRoom = async () => {
  const res = await fetchGetWrapper<FinishRoomStatusResponse>('/room-finish');

  return res.status;
};

export const exitRoom: ExitRoom = async () => {
  await fetchPostWrapper('/exit-room');
};
