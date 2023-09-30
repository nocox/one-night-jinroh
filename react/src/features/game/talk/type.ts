import { z } from 'zod';
import { gameIndexSchema } from '@/features/game/type';
import type { GameIndex } from '@/features/game/type';

/**
 * 話し合いページ読み込み時のレスポンス
 */
type Co = {
  id: number;
  role: string;
};

export type TalkIndexResponseBody = {
  gameId: number;
  gameIndex: GameIndex;
  cos: Co[];
};

const coSchema = z.object({
  id: z.number(),
  role: z.string(),
});

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
