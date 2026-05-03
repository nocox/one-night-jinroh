import type { UseQueryResult } from '@tanstack/react-query';
import { fetchGetWrapper, useQueryWrapper } from '@/api';
import {
  type FetchGameIndexParam,
  type GameIndexResponse,
} from '@/features/game/type';

export const useGameIndex = (
  param: FetchGameIndexParam,
  gameId: number | undefined,
): UseQueryResult<GameIndexResponse | undefined> =>
  useQueryWrapper({
    queryKey: ['game-index', gameId, param],
    queryFn: async () => {
      if (gameId !== undefined) {
        return await fetchGetWrapper<GameIndexResponse>('/game-index', {
          term: param,
        });
      }
    },
    enabled: gameId !== undefined,
  });
