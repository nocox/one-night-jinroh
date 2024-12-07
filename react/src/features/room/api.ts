import type { ExitRoom, FinishRoom, FinishRoomStatusResponse } from './type';
import { fetchGetWrapper, fetchPostWrapper } from '@/api';

export const finishRoom: FinishRoom = async () => {
  const res = await fetchGetWrapper<FinishRoomStatusResponse>('/room-finish');

  return res.status;
};

export const exitRoom: ExitRoom = async () => {
  await fetchPostWrapper('/exit-room');
};
