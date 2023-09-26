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
