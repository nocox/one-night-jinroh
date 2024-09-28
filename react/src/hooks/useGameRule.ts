import { useEffect, useState } from 'react';
import { fetchWraper } from '@/api';
import type { GameRule, GameRuleList } from '@/type';

export const useGameRule = (
  gameId: number | undefined,
): { gameRuleList: GameRule[] } => {
  const [gameRuleList, setGameRuleList] = useState<GameRule[]>([]);

  useEffect(() => {
    const fetchGameRuleListAsync = async () => {
      if (gameId !== undefined) {
        const gameRuleList = await fetchWraper<GameRuleList>(
          `/game-rule/${gameId}`,
        );

        setGameRuleList(gameRuleList.roleList);
      }
    };

    void fetchGameRuleListAsync();
  }, [gameId]);

  return { gameRuleList };
};
