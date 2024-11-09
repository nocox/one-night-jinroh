import { useEffect, useState } from 'react';
import { fetchGetWrapper } from '@/api';
import type { NightIndexResponseBody } from '@/features/game/night/type';

export const useNightData = (): {
  gameId: number | undefined;
  doneNightAct: boolean | undefined;
} => {
  const [gameId, setGameId] = useState<number | undefined>(undefined);
  const [doneNightAct, setDoneNightAct] = useState<boolean | undefined>(
    undefined,
  );

  useEffect(() => {
    const fetchData = async () => {
      const { gameId, doneNightAct } =
        await fetchGetWrapper<NightIndexResponseBody>('/night-index');
      setGameId(gameId);
      setDoneNightAct(doneNightAct);
    };
    void fetchData();
  }, []);

  return { gameId, doneNightAct };
};
