import { useEffect, useState } from 'react';
import { fetchGameRuleList } from '@/api';
import type { GameRule } from '@/type';

export const useGameRule = (): { gameRuleList: GameRule[] | undefined } => {
  const [gameRuleList, setGameRuleList] = useState<GameRule[] | undefined>(
    undefined,
  );

  useEffect(() => {
    const fetchGameRuleListAsync = async () => {
      const gameRuleList = await fetchGameRuleList();
      setGameRuleList(gameRuleList.roleList);
    };

    void fetchGameRuleListAsync();
  }, []);

  return { gameRuleList };
};
