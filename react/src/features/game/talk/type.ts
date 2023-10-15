import { z } from 'zod';
import type { Character } from '@/features/game/character';
import { coSchema, gameIndexSchema } from '@/features/game/type';
import type { Co, GameIndex, RoleBean } from '@/features/game/type';

/**
 * 話し合いページ読み込み時のレスポンス
 */

export type TalkIndexResponseBody = {
  gameId: number;
  gameIndex: GameIndex;
  cos: Co[];
};

const talkIndexResponseBodySchema = z.object({
  gameId: z.number(),
  gameIndex: gameIndexSchema,
  cos: z.array(coSchema),
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
  co: Co;
};

/**
 * COリクエスト用
 */

type PostCoDto = {
  playerId: number;
  role: Character['EnglishName'];
};

export type PostCo = (dto: PostCoDto) => Promise<void>;
