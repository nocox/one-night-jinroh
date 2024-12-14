import { useQuery } from '@tanstack/react-query';
import { fetchGetWrapper } from '@/api';
import {
  type GameParticipant,
  type RoleBean,
  type FetchGameIndexParam,
  type GameIndexResponse,
} from '@/features/game/type';

export const useGameIndex = (
  param: FetchGameIndexParam,
  gameId: number | undefined,
): {
  hostFlag: boolean | undefined;
  nightActLog: string | undefined;
  otherPlayerList: GameParticipant[] | undefined;
  playerId: number | undefined;
  playerName: string | undefined;
  playerRole: RoleBean | undefined;
  error: Error | null;
} => {
  const { data, error } = useQuery({
    queryKey: ['game-index', gameId],
    queryFn: async () => {
      if (gameId !== undefined) {
        return await fetchGetWrapper<GameIndexResponse>('/game-index', {
          term: param,
        });
      }
    },
    enabled: gameId !== undefined,
  });

  switch (data?.type) {
    case 'GameIndex':
      break;
    case 'TermIsDifferent':
      window.location.href = '/' + data.term;
      break;
    case 'NotStared':
      window.location.href = '/room';
      break;
  }

  return {
    hostFlag: data?.type === 'GameIndex' ? data.hostFlag : undefined,
    nightActLog:
      data?.type === 'GameIndex' ? data.nightActLog ?? '' : undefined,
    otherPlayerList:
      data?.type === 'GameIndex' ? data.otherPlayerList : undefined,
    playerId: data?.type === 'GameIndex' ? data.playerId : undefined,
    playerName: data?.type === 'GameIndex' ? data.playerName : undefined,
    playerRole: data?.type === 'GameIndex' ? data.playerRole : undefined,
    error,
  };
};
