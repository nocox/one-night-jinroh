import type { CoRole, GameIndex, RoleBean } from '@/features/game/type';

export type GameParticipantWithVoteBean = {
  id: number;
  name: string;
  role: RoleBean;
  hostFlag: boolean;
  voteCount: number;
  isMyself: boolean;
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
  cos: CoRole[];
};

export type FetchTallyIndexResponseBody = () => Promise<TallyIndexResponseBody>;

export type FetchResult = () => Promise<void>;
