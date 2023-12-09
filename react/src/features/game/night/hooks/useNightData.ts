import { useEffect, useState } from 'react';
import { fetchNightIndex } from '@/features/game/night/api';

export const useNightData = (): {
  gameId: number | undefined;
  doneNightAct: boolean | undefined;
} => {
  const [gameId, setGameId] = useState<number | undefined>(undefined);
  const [doneNightAct, setDoneNightAct] = useState<boolean | undefined>(
    undefined,
  );

  useEffect(() => {
    const fetchNightIndexAsync = async () => {
      const { gameId, doneNightAct } = await fetchNightIndex();
      setGameId(gameId);
      setDoneNightAct(doneNightAct);
    };
    void fetchNightIndexAsync();
  }, []);

  return { gameId, doneNightAct };
};
