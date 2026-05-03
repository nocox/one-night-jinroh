import { type UseQueryResult } from '@tanstack/react-query';
import { fetchGetWrapper, useQueryWrapper } from '@/api';
import type { GameRuleList } from '@/type';

export const useGameRule = (
  gameId: number | undefined,
): UseQueryResult<GameRuleList | undefined> =>
  useQueryWrapper({
    queryKey: ['game-rule', gameId],
    queryFn: async () => {
      if (gameId !== undefined) {
        return await fetchGetWrapper<GameRuleList>(
          `/game-rule/${gameId.toString()}`,
        );
      }
    },
    enabled: gameId !== undefined,
  });
