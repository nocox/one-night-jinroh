import { z } from 'zod';
import type { RoleBean } from '@/features/game/type';

/**
 * 夜の行動ページ読み込み時
 */
export type NightIndexResponseBody = {
  doneNightAct: boolean;
  gameId: number;
};

const nightIndexResponseBodySchema = z.object({
  doneNightAct: z.boolean(),
  gameId: z.number(),
});

export const isNightIndexResponseBody = (
  value: unknown,
): value is NightIndexResponseBody => {
  return nightIndexResponseBodySchema.safeParse(value).success;
};

export type FetchNightIndex = () => Promise<NightIndexResponseBody>;

/**
 * 夜の行動ページでの怪盗の行動
 */
export type NightKaitoResult = {
  actLog: string;
  selectedParticipantId: number;
};

const nightKaitoResultSchema = z.object({
  actLog: z.string(),
  selectedParticipantId: z.number(),
});

export const isNightKaitoResult = (
  value: unknown,
): value is NightKaitoResult => {
  return nightKaitoResultSchema.safeParse(value).success;
};

type NightKaitoActionDto = {
  participantId: number;
};

export type PostNightKaitoAction = (
  dto: NightKaitoActionDto,
) => Promise<NightKaitoResult>;
export type FetchNightKaitoActionResult = () => Promise<
  NightKaitoResult | undefined
>;
export type FetchDoneNightAct = () => Promise<boolean>;

/**
 * 夜の行動ページでの占い師の行動
 */
export const uranaiStatus = {
  PLAYER: 'PLAYER',
  HOLIDAY_ROLES: 'HOLIDAY_ROLES',
  NOT_CHOOSE: 'NOT_CHOOSE',
} as const;

export type UranaiStatus = (typeof uranaiStatus)[keyof typeof uranaiStatus];

type User = {
  userId: number;
  userName: string;
};
export type NightUranaiResult = {
  status: UranaiStatus;
  participantId: number | null;
  roles: RoleBean[];
  user: User | null;
};

const nightUranaiResultSchema = z.object({
  status: z.string(),
  participantId: z.number().nullable(),
  roles: z.array(
    z.object({
      roleId: z.number(),
      roleName: z.string(),
    }),
  ),
  user: z
    .object({
      userId: z.number(),
      userName: z.string(),
    })
    .nullable(),
});

export const isNightUranaiResult = (
  value: unknown,
): value is NightUranaiResult => {
  return nightUranaiResultSchema.safeParse(value).success;
};

type PostNightUranaishiActionDto = {
  status: UranaiStatus;
  participantId: number | undefined;
};

export type PostNightUranaishiAction = (
  dto: PostNightUranaishiActionDto,
) => Promise<NightUranaiResult>;
export type FetchNightUranaishiAction = () => Promise<
  NightUranaiResult | undefined
>;

/**
 * 夜の行動ページでの人狼
 */
export type NightJinrohPlayers = {
  playerNames: string[];
};

const nightJinrohPlayersSchema = z.object({
  playerNames: z.array(z.string()),
});

export const isNightJinrohPlayers = (
  value: unknown,
): value is NightJinrohPlayers => {
  return nightJinrohPlayersSchema.safeParse(value).success;
};

export type FetchNightJinrohPlayers = () => Promise<NightJinrohPlayers>;
