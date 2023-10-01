import { z } from 'zod';
import type { Character } from '@/features/game/character';
import { gameIndexSchema } from '@/features/game/type';
import type { GameIndex, Role } from '@/features/game/type';

/**
 * 話し合いページ読み込み時のレスポンス
 */
export type Co = {
  id: number;
  role: string;
};

export type CoBeans = {
  coBeans: Co[];
}

export type TalkIndexResponseBody = {
  gameId: number;
  gameIndex: GameIndex;
  cos: Co[];
};

const coSchema = z.object({
  id: z.number(),
  role: z.string(),
});

const coBeansSchema = z.object({
  coBeans: z.array(coSchema)
});

const talkIndexResponseBodySchema = z.object({
  gameId: z.number(),
  gameIndex: gameIndexSchema,
  cos: z.array(coSchema),
});

export const isCoBeans = (value: unknown): value is CoBeans => {
  return coBeansSchema.safeParse(value).success;
}

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
  role: Role;
  co: Co;
};

/**
 * COリクエスト用
 */

type PostCoDto = {
  playerId: number;
  role: Character['EnglishName'];
}

export type PostCo = (dto: PostCoDto) => Promise<void>;