import { useEffect, useState } from 'react';
import { fetchNightIndex } from '@/features/game/night/api';
import type { GameIndex } from '@/features/game/type';

export const useNightData = (): {
  gameId: number | undefined;
  gameIndex: GameIndex | undefined;
  doneNightAct: boolean | undefined;
} => {
  const [gameId, setGameId] = useState<number | undefined>(undefined);
  const [gameIndex, setGameIndex] = useState<GameIndex | undefined>(undefined);
  const [doneNightAct, setDoneNightAct] = useState<boolean | undefined>(
    undefined,
  );

  useEffect(() => {
    const fetchNightIndexAsync = async () => {
      const { gameId, gameIndex, doneNightAct } = await fetchNightIndex();
      setGameId(gameId);
      setGameIndex(gameIndex);
      setDoneNightAct(doneNightAct);
    };
    void fetchNightIndexAsync();
  }, []);

  return { gameId, gameIndex, doneNightAct };
};
