import { z } from 'zod';

type JoinRoomDto = {
  roomId: string;
};

type JoinedRoomStatus =
  | 'JOIN_SUCCESS'
  | 'ROOM_NOT_EXIST'
  | 'PARTICPANT_LIMIT'
  | 'ALREADY_JOINED'
  | 'OTHER_ROOM_JOINED';

const joinedRoomStatusSchema = z.union([
  z.literal('JOIN_SUCCESS'),
  z.literal('ROOM_NOT_EXIST'),
  z.literal('PARTICPANT_LIMIT'),
  z.literal('ALREADY_JOINED'),
  z.literal('OTHER_ROOM_JOINED'),
]);

export const isJoinedRoomStatus = (
  value: unknown,
): value is JoinedRoomStatus => {
  return joinedRoomStatusSchema.safeParse(value).success;
};

type CreateRoomStatus = 'CREATE_ROOM_SUCCESS' | 'OTHER_ROOM_JOINED';

const createRoomStatusSchema = z.union([
  z.literal('CREATE_ROOM_SUCCESS'),
  z.literal('OTHER_ROOM_JOINED'),
]);

export const isCreateRoomStatus = (
  value: unknown,
): value is CreateRoomStatus => {
  return createRoomStatusSchema.safeParse(value).success;
};

export type JoinRoom = (dto: JoinRoomDto) => Promise<JoinedRoomStatus>;
export type CreateRoom = () => Promise<CreateRoomStatus>;
