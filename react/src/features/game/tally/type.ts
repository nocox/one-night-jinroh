import { z } from 'zod';
import type { CoBean, GameIndex, RoleBean } from '@/features/game/type';
import {
  coBeanSchema,
  gameIndexSchema,
  roleBeanSchema,
} from '@/features/game/type';

export type GameParticipantWithVoteBean = {
  id: number;
  name: string;
  role: RoleBean;
  hostFlag: boolean;
  voteCount: number;
};

type TallyResultBean = {
  selectedPlayers: GameParticipantWithVoteBean[];
  players: GameParticipantWithVoteBean[];
  peacefulFlag: boolean;
};

export type TallyIndexResponseBody = {
  gameId: number;
  gameIndex: GameIndex;
  tallyResult: TallyResultBean;
  cos: CoBean[];
};

const gameParticipantWithVoteBeanSchema = z.object({
  id: z.number(),
  name: z.string(),
  role: roleBeanSchema,
  hostFlag: z.boolean(),
  voteCount: z.number(),
});

const tallyIndexResponseBodySchema = z.object({
  gameId: z.number(),
  gameIndex: gameIndexSchema,
  tallyResult: z.object({
    selectedPlayers: z.array(gameParticipantWithVoteBeanSchema),
    players: z.array(gameParticipantWithVoteBeanSchema),
    peacefulFlag: z.boolean(),
  }),
  cos: z.array(coBeanSchema),
});

export const isTallyIndexResponseBody = (
  value: unknown,
): value is TallyIndexResponseBody => {
  return tallyIndexResponseBodySchema.safeParse(value).success;
};

export type FetchTallyIndexResponseBody = () => Promise<TallyIndexResponseBody>;
