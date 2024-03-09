import { z } from 'zod';
import type { RoleEnglishName } from '@/features/role';

export type GameParticipantWithResultBean = {
  playerName: string;
  role: RoleEnglishName;
  coRole: RoleEnglishName | '';
  winOrLose: 'win' | 'lose';
  myself: boolean;
  comment: string;
};

export type JudgeResult = {
  text: string;
  imagePath: string;
};

export type ShowResultTermIndexBean = {
  gameId: number;
  hostFlg: boolean;
  judge:
    | 'FAIL_PEACE_VILLAGE'
    | 'SIMPLE_JINROH_WIN'
    | 'SIMPLE_VILLAGE_WIN'
    | 'SUCCESS_HIDE_JINROH_WIN'
    | 'SUCCESS_PEACE_VILLAGE'
    | 'TURIBITO_WIN';
  participants: GameParticipantWithResultBean[];
  holidayRoles: RoleEnglishName[];
};

const showResultTermIndexBeanSchema = z.object({
  gameId: z.number(),
  hostFlg: z.boolean(),
  judge: z.union([
    z.literal('FAIL_PEACE_VILLAGE'),
    z.literal('SIMPLE_JINROH_WIN'),
    z.literal('SIMPLE_VILLAGE_WIN'),
    z.literal('SUCCESS_HIDE_JINROH_WIN'),
    z.literal('SUCCESS_PEACE_VILLAGE'),
    z.literal('TURIBITO_WIN'),
  ]),
  participants: z.array(
    z.object({
      playerName: z.string(),
      role: z.union([
        z.literal('murabito'),
        z.literal('jinroh'),
        z.literal('uranaishi'),
        z.literal('kaito'),
        z.literal('kyojin'),
        z.literal('turibito'),
      ]),
      coRole: z.union([
        z.literal('murabito'),
        z.literal('jinroh'),
        z.literal('uranaishi'),
        z.literal('kaito'),
        z.literal('kyojin'),
        z.literal('turibito'),
        z.literal(''),
      ]),
      winOrLose: z.union([z.literal('win'), z.literal('lose')]),
      myself: z.boolean(),
      comment: z.string(),
    }),
  ),
  holidayRoles: z.array(
    z.union([
      z.literal('murabito'),
      z.literal('jinroh'),
      z.literal('uranaishi'),
      z.literal('kaito'),
      z.literal('kyojin'),
      z.literal('turibito'),
    ]),
  ),
});

export const isShowResultTermIndexBean = (
  value: unknown,
): value is ShowResultTermIndexBean => {
  return showResultTermIndexBeanSchema.safeParse(value).success;
};

export type FetchShowResultTermIndex = () => Promise<ShowResultTermIndexBean>;
export type FetchReturnRoom = () => Promise<void>;
