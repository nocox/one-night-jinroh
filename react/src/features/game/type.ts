import { z } from 'zod';
import type { RoleEnglishName, RoleJapaneseName } from '@/features/role';

type ParticipantId = number;

/**
 * 人狼ゲームにおけるプレーヤーのCOした役職および参加者IDを表す
 */
export type CoRole = {
  id: ParticipantId;
  role: RoleEnglishName; // TODO: unknownの扱いについて検討する
};

/**
 * バックエンドからのレスポンスの型
 */
export type CoBeans = {
  coBeans: CoRole[];
};

export const coRoleSchema = z.object({
  id: z.number(),
  role: z.union([
    z.literal('murabito'),
    z.literal('jinroh'),
    z.literal('uranaishi'),
    z.literal('kaito'),
    z.literal('kyojin'),
    z.literal('turibito'),
  ]),
});

export const coBeansSchema = z.object({
  coBeans: z.array(coRoleSchema),
});

export const isCoBeans = (value: unknown): value is CoBeans => {
  return coBeansSchema.safeParse(value).success;
};

export type RoleBean = {
  roleId: number;
  roleName: RoleJapaneseName;
};

export const roleBeanSchema = z.object({
  roleId: z.number(),
  roleName: z.union([
    z.literal('人狼'),
    z.literal('村人'),
    z.literal('占い師'),
    z.literal('怪盗'),
    z.literal('狂人'),
    z.literal('吊り人'),
    z.literal('不明'),
  ]),
});

export type GameParticipant = {
  hostFlag: boolean;
  id: number;
  name: string;
  role: RoleBean;
};

export type GameParticipantWithCoRole = GameParticipant & {
  co: CoRole;
};

export const gameParticipantSchema = z.object({
  hostFlag: z.boolean(),
  id: z.number(),
  name: z.string(),
  role: roleBeanSchema,
});

export type GameIndexResponse = GameIndex | TermIsDifferent | NotStared

export type GameIndex = {
  type: "GameIndex";
  hostFlag: boolean;
  nightActLog: string | null;
  otherPlayerList: GameParticipant[];
  playerId: number;
  playerName: string;
  playerRole: RoleBean;
};

export type TermIsDifferent = {
  type: "TermIsDifferent"
  term: string
}

export type NotStared = {
  type: "NotStared"
  errorMessage: string
}

const gameIndexSchemaSchema = z.object({
  hostFlag: z.boolean(),
  nightActLog: z.string().nullable(),
  otherPlayerList: z.array(gameParticipantSchema),
  playerId: z.number(),
  playerName: z.string(),
  playerRole: roleBeanSchema,
});

const termIsDifferentSchema = z.object({
  term: z.union([
    z.literal("night"),
    z.literal("talk"),
    z.literal("vote"),
    z.literal("tally"),
    z.literal("result")
  ])
});

const notStaredSchema = z.object({
  errorMessage: z.string()
});

export const gameIndexResponseSchema = z.union([gameIndexSchemaSchema, termIsDifferentSchema, notStaredSchema])

export const isGameIndexSchema = (value: unknown): value is GameIndexResponse => {
  return gameIndexResponseSchema.safeParse(value).success;
};

export type FetchGameIndexParam =
  | 'night'
  | 'talk'
  | 'vote'
  | 'tally'
  | 'result';

export type FetchGameIndex = (param: FetchGameIndexParam) => Promise<GameIndexResponse>;
