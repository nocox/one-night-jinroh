import { useEffect, useState } from 'react';
import { fetchGameRuleList } from '@/api';
import type { GameRule } from '@/type';

export const useGameRule = (
  gameId: number | undefined,
): { gameRuleList: GameRule[] } => {
  const [gameRuleList, setGameRuleList] = useState<GameRule[]>([]);

  useEffect(() => {
    const fetchGameRuleListAsync = async () => {
      if (gameId !== undefined) {
        const gameRuleList = await fetchGameRuleList(gameId);
        setGameRuleList(gameRuleList.roleList);
      }
    };

    void fetchGameRuleListAsync();
  }, [gameId]);

  return { gameRuleList };
};
