import type { ExitRoom, FinishRoom, FinishRoomStatus } from './type';
import { fetchGetWrapper, fetchPostWrapper } from '@/api';

export const finishRoom: FinishRoom = async () => {
  const res = await fetchGetWrapper<Response>('/room-finish');

  return (await res.text()) as FinishRoomStatus;
};

export const exitRoom: ExitRoom = async () => {
  await fetchPostWrapper('/exit-room');
};
