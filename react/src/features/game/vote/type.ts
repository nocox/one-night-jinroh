import type { CoRole, GameIndex, GameParticipant } from '@/features/game/type';

export type VoteIndex = {
  canVotePlayers: GameParticipant[];
  votingDestination: number | null;
};

export type VoteIndexRequestBody = {
  gameId: number;
  gameIndex: GameIndex;
  voteIndex: VoteIndex;
  cos: CoRole[];
};
