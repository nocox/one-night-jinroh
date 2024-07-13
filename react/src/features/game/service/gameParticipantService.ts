import type {
  CoRole,
  GameParticipant,
  GameParticipantWithCoRole,
  RoleBean,
} from '@/features/game/type';

/**
 * 参加者にCoRoleを付与したGameParticipantWithCoRoleの配列を返す
 */
export const toGameParticipantsWithCoRole = (
  otherPlayerList: GameParticipant[],
  hostFlag: boolean,
  playerId: number,
  playerName: string,
  playerRole: RoleBean,
  cos: CoRole[],
): GameParticipantWithCoRole[] => [
  {
    hostFlag,
    id: playerId,
    name: playerName,
    role: playerRole,
    co: cos.find((co) => co.id === playerId) ?? null,
    isMyself: true,
  },
  ...otherPlayerList.map((otherPlayer) => ({
    hostFlag: otherPlayer.hostFlag,
    id: otherPlayer.id,
    name: otherPlayer.name,
    role: otherPlayer.role,
    co: cos.find((co) => co.id === playerId) ?? null,
    isMyself: false,
  })),
];
