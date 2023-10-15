import { z } from 'zod';
import { coBeanSchema, gameIndexSchema } from '@/features/game/type';
import type { CoBean, GameIndex, RoleBean } from '@/features/game/type';
import type { Role } from '@/features/role';

/**
 * 話し合いページ読み込み時のレスポンス
 */

export type TalkIndexResponseBody = {
  gameId: number;
  gameIndex: GameIndex;
  cos: CoBean[];
};

const talkIndexResponseBodySchema = z.object({
  gameId: z.number(),
  gameIndex: gameIndexSchema,
  cos: z.array(coBeanSchema),
});

export const isTalkIndexResponseBody = (
  value: unknown,
): value is TalkIndexResponseBody => {
  return talkIndexResponseBodySchema.safeParse(value).success;
};

export type FetchTalkIndex = () => Promise<TalkIndexResponseBody>;

/**
 * プレーヤー
 */
export type Player = {
  id: number;
  name: string;
  role: RoleBean;
  co: CoBean;
};

/**
 * COリクエスト用
 */

type PostCoDto = {
  playerId: number;
  role: Role['englishName'];
};

export type PostCo = (dto: PostCoDto) => Promise<void>;
