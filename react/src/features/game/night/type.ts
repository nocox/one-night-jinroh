import type { RoleBean } from '@/features/game/type';

/**
 * 夜の行動ページ読み込み時
 */
export type NightIndexResponseBody = {
  doneNightAct: boolean;
  gameId: number;
};

export type FetchNightIndex = () => Promise<NightIndexResponseBody>;

/**
 * 夜の行動ページでの怪盗の行動
 */
export type NightKaitoResult = {
  actLog: string;
  selectedParticipantId: number;
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
export type FetchDoneNightAct = () => Promise<boolean>;

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

type PostNightUranaishiActionDto = {
  status: UranaiStatus;
  participantId: number | undefined;
};

export type PostNightUranaishiAction = (
  dto: PostNightUranaishiActionDto,
) => Promise<NightUranaiResult>;
export type FetchNightUranaishiAction = () => Promise<
  NightUranaiResult | undefined
>;

/**
 * 夜の行動ページでの人狼
 */
export type NightJinrohPlayers = {
  playerNames: string[];
};

export type FetchNightJinrohPlayers = () => Promise<NightJinrohPlayers>;
