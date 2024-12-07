import { useEffect, useState } from 'react';
import { fetchGetWrapper } from '@/api';
import type { GameRule, GameRuleList } from '@/type';

export const useGameRule = (
  gameId: number | undefined,
): { gameRuleList: GameRule[] } => {
  const [gameRuleList, setGameRuleList] = useState<GameRule[]>([]);

  useEffect(() => {
    const fetchGameRuleListAsync = async () => {
      if (gameId !== undefined) {
        const gameRuleList = await fetchGetWrapper<GameRuleList>(
          `/game-rule/${gameId.toString()}`,
          {},
        );
        setGameRuleList(gameRuleList.roleList);
      }
    };

    void fetchGameRuleListAsync();
  }, [gameId]);

  return { gameRuleList };
};
