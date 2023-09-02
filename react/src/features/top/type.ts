import { z } from 'zod';

type JoinRoomDto = {
  roomId: string;
};

type JoinedRoomStatus = 'JOIN_SUCCESS' | 'ROOM_NOT_EXIST' | 'PARTICPANT_LIMIT';

const joinedRoomStatusSchema = z.union([
  z.literal('JOIN_SUCCESS'),
  z.literal('ROOM_NOT_EXIST'),
  z.literal('PARTICPANT_LIMIT'),
]);

export const isJoinedRoomStatus = (
  value: unknown,
): value is JoinedRoomStatus => {
  return joinedRoomStatusSchema.safeParse(value).success;
};

export type JoinRoom = (dto: JoinRoomDto) => Promise<JoinedRoomStatus>;
export type CreateRoom = () => Promise<void>;
