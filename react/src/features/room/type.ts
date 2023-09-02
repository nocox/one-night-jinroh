import { z } from 'zod';

export type RoomIndexResponseBody = {
  uuid: string;
  userList: User[];
  hostFlg: boolean;
  myselfUserId: number;
};

export type User = {
  userId: number;
  name: string;
  hostFlg: boolean;
};

export type GameStartStatus = 'SUCCESS' | 'NOT_ENOUGH_PARTICIPANTS';

const userSchema = z.object({
  userId: z.number(),
  name: z.string(),
  hostFlg: z.boolean(),
});

const roomIndexResponseBodySchema = z.object({
  uuid: z.string(),
  userList: z.array(userSchema),
  hostFlg: z.boolean(),
  myselfUserId: z.number(),
});

const gameStartStatusSchema = z.union([
  z.literal('SUCCESS'),
  z.literal('NOT_ENOUGH_PARTICIPANTS'),
]);

export const isRoomIndexResponseBody = (
  value: unknown,
): value is RoomIndexResponseBody => {
  return roomIndexResponseBodySchema.safeParse(value).success;
};

export const isGameStartStatus = (value: unknown): value is GameStartStatus => {
  return gameStartStatusSchema.safeParse(value).success;
};

export type FetchRoomIndex = () => Promise<RoomIndexResponseBody>;
export type FetchGameStart = () => Promise<GameStartStatus>;
export type FinishRoom = () => Promise<void>;