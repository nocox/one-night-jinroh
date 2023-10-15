import { z } from 'zod';

export type Co = {
  id: number;
  role: string;
};

export type CoBeans = {
  coBeans: Co[];
};

export const coSchema = z.object({
  id: z.number(),
  role: z.string(),
});

export const coBeansSchema = z.object({
  coBeans: z.array(coSchema),
});

export const isCoBeans = (value: unknown): value is CoBeans => {
  return coBeansSchema.safeParse(value).success;
};

export type RoleBean = {
  roleId: number;
  roleName: '人狼' | '村人' | '占い師' | '怪盗' | '狂人' | '吊人' | '不明';
};

const roleSchema = z.object({
  roleId: z.number(),
  roleName: z.string(),
});

export type OtherPlayer = {
  hostFlag: boolean;
  id: number;
  name: string;
  role: RoleBean;
};

export const otherPlayerSchema = z.object({
  hostFlag: z.boolean(),
  id: z.number(),
  name: z.string(),
  role: roleSchema,
});

export type GameIndex = {
  hostFlag: boolean;
  nightActLog: string | null;
  otherPlayerList: OtherPlayer[];
  playerId: number;
  playerName: string;
  playerRole: RoleBean;
};

export const gameIndexSchema = z.object({
  hostFlag: z.boolean(),
  nightActLog: z.string().nullable(),
  otherPlayerList: z.array(otherPlayerSchema),
  playerId: z.number(),
  playerName: z.string(),
  playerRole: roleSchema,
});
