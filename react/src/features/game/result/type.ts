import type { RoleEnglishName } from '@/features/role';

export type GameParticipantWithResultBean = {
  playerName: string;
  role: RoleEnglishName;
  coRole: RoleEnglishName | '';
  winOrLose: 'win' | 'lose';
  myself: boolean;
  comment: string;
};

export type JudgeResult = {
  text: string;
  imagePath: string;
};

export type ShowResultTermIndexBean = {
  gameId: number;
  hostFlg: boolean;
  judge:
    | 'FAIL_PEACE_VILLAGE'
    | 'SIMPLE_JINROH_WIN'
    | 'SIMPLE_VILLAGE_WIN'
    | 'SUCCESS_HIDE_JINROH_WIN'
    | 'SUCCESS_PEACE_VILLAGE'
    | 'TURIBITO_WIN';
  participants: GameParticipantWithResultBean[];
  holidayRoles: RoleEnglishName[];
};
