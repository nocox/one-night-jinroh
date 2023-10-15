import { z } from 'zod';
import type { CoBean, GameIndex, OtherPlayer } from '@/features/game/type';
import {
  coBeanSchema,
  gameIndexSchema,
  otherPlayerSchema,
} from '@/features/game/type';

export type VoteIndex = {
  canVotePlayers: OtherPlayer[];
  votingDestination: number | null;
};

const voteIndexSchema = z.object({
  canVotePlayers: z.array(otherPlayerSchema),
  votingDestination: z.number().nullable(),
});

export type VoteIndexRequestBody = {
  gameId: number;
  gameIndex: GameIndex;
  voteIndex: VoteIndex;
  cos: CoBean[];
};

const voteIndexRequestBodySchema = z.object({
  gameId: z.number(),
  gameIndex: gameIndexSchema,
  voteIndex: voteIndexSchema,
  cos: z.array(coBeanSchema),
});

export const isVoteIndexRequestBody = (
  value: unknown,
): value is VoteIndexRequestBody => {
  return voteIndexRequestBodySchema.safeParse(value).success;
};

export type FetchVoteIndex = () => Promise<VoteIndexRequestBody>;

type PostVoteFormDto = {
  gameParticipantId: number;
};

export type PostVoteForm = (dto: PostVoteFormDto) => Promise<void>;