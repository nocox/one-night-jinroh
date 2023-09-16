import { z } from 'zod';
import type { GameIndex } from '@/features/game/type';
import { gameIndexSchema } from '@/features/game/type';

export type NightIndexResponseBody = {
  doneNightAct: boolean;
  gameId: number;
  gameIndex: GameIndex;
};

const nightIndexResponseBodySchema = z.object({
  doneNightAct: z.boolean(),
  gameId: z.number(),
  gameIndex: gameIndexSchema,
});

export const isNightIndexResponseBody = (
  value: unknown,
): value is NightIndexResponseBody => {
  return nightIndexResponseBodySchema.safeParse(value).success;
};

export type FetchNightIndex = () => Promise<NightIndexResponseBody>;
