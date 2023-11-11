import { z } from 'zod';

export type CoBean = {
  id: number;
  role: string;
};

export type CoBeans = {
  coBeans: CoBean[];
};

export const coBeanSchema = z.object({
  id: z.number(),
  role: z.string(),
});

export const coBeansSchema = z.object({
  coBeans: z.array(coBeanSchema),
});

export const isCoBeans = (value: unknown): value is CoBeans => {
  return coBeansSchema.safeParse(value).success;
};

export type RoleBean = {
  roleId: number;
  roleName: '人狼' | '村人' | '占い師' | '怪盗' | '狂人' | '吊人' | '不明';
};

export const roleBeanSchema = z.object({
  roleId: z.number(),
  roleName: z.union([
    z.literal('人狼'),
    z.literal('村人'),
    z.literal('占い師'),
    z.literal('怪盗'),
    z.literal('狂人'),
    z.literal('吊人'),
    z.literal('不明'),
  ]),
});

export type GameParticipant = {
  hostFlag: boolean;
  id: number;
  name: string;
  role: RoleBean;
};

export const gameParticipantSchema = z.object({
  hostFlag: z.boolean(),
  id: z.number(),
  name: z.string(),
  role: roleBeanSchema,
});

export type GameIndex = {
  hostFlag: boolean;
  nightActLog: string | null;
  otherPlayerList: GameParticipant[];
  playerId: number;
  playerName: string;
  playerRole: RoleBean;
};

export const gameIndexSchema = z.object({
  hostFlag: z.boolean(),
  nightActLog: z.string().nullable(),
  otherPlayerList: z.array(gameParticipantSchema),
  playerId: z.number(),
  playerName: z.string(),
  playerRole: roleBeanSchema,
});

export const isGameIndexSchema = (value: unknown): value is GameIndex => {
  return gameIndexSchema.safeParse(value).success;
};

export type FetchGameIndexParam =
  | 'night'
  | 'talk'
  | 'vote'
  | 'tally'
  | 'result';

export type FetchGameIndex = (param: FetchGameIndexParam) => Promise<GameIndex>;
