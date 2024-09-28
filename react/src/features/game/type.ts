import type { RoleEnglishName, RoleJapaneseName } from '@/features/role';

type ParticipantId = number;

/**
 * 人狼ゲームにおけるプレーヤーのCOした役職および参加者IDを表す
 */
export type CoRole = {
  id: ParticipantId;
  role: RoleEnglishName; // TODO: unknownの扱いについて検討する
};

/**
 * バックエンドからのレスポンスの型
 */
export type CoBeans = {
  coBeans: CoRole[];
};

export type RoleBean = {
  roleId: number;
  roleName: RoleJapaneseName;
};

export type GameParticipant = {
  hostFlag: boolean;
  id: number;
  name: string;
  role: RoleBean;
  isMyself: boolean;
};

export type GameParticipantWithCoRole = GameParticipant & {
  co: CoRole | null;
};

export type GameIndexResponse = GameIndex | TermIsDifferent | NotStared;

export type GameIndex = {
  type: 'GameIndex';
  hostFlag: boolean;
  nightActLog: string | null;
  otherPlayerList: GameParticipant[];
  playerId: number;
  playerName: string;
  playerRole: RoleBean;
};

export type TermIsDifferent = {
  type: 'TermIsDifferent';
  term: string;
};

export type NotStared = {
  type: 'NotStared';
  errorMessage: string;
};

export type FetchGameIndexParam =
  | 'night'
  | 'talk'
  | 'vote'
  | 'tally'
  | 'result';

export type FetchGameIndex = (
  param: FetchGameIndexParam,
) => Promise<GameIndexResponse>;
