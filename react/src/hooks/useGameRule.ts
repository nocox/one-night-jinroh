import { useQuery } from '@tanstack/react-query';
import { fetchGetWrapper } from '@/api';
import type { GameRule, GameRuleList } from '@/type';

export const useGameRule = (
  gameId: number | undefined,
): { gameRuleList: GameRule[] } => {
  const { data } = useQuery({
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

  return { gameRuleList: data?.roleList ?? [] };
};
