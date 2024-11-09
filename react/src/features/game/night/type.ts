import type { RoleBean } from '@/features/game/type';

export type FetchGameIdResponseBody =
    {
      resultCode: "IN_GAME"
      gameId: number
    } |
    {
      resultCode: "NOT_IN_GAME"
    }

/**
 * 夜の行動ページ読み込み時
 */
export type NightIndexResponseBody = {
  doneNightAct: boolean;
  gameId: number;
};

/**
 * 夜の行動ページでの怪盗の行動
 */
export type NightKaitoResult = {
  actLog: string;
  selectedParticipantId: number;
};

/**
 * 夜の行動ページでの占い師の行動
 */
export const uranaiStatus = {
  PLAYER: 'PLAYER',
  HOLIDAY_ROLES: 'HOLIDAY_ROLES',
  NOT_CHOOSE: 'NOT_CHOOSE',
} as const;

export type UranaiStatus = (typeof uranaiStatus)[keyof typeof uranaiStatus];

type User = {
  userId: number;
  userName: string;
};
export type NightUranaiResult = {
  status: UranaiStatus;
  participantId: number | null;
  roles: RoleBean[];
  user: User | null;
};

/**
 * 夜の行動ページでの人狼
 */
export type NightJinrohPlayers = {
  playerNames: string[];
};
