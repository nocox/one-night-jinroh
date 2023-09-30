import { useEffect, useState } from 'react';
import { NightTemplate } from './NightTemplate';
import { useNightData } from './hooks/useNightData';
import { Loading } from '@/components';
import { useWebSocket } from '@/hooks';
import type { Subscribe } from '@/type';

export const NightPage: React.FC = () => {
  const nightIndexResponseBody = useNightData();
  const [gameId, setGameId] = useState<number>(-1);

  useEffect(() => {
    if (nightIndexResponseBody !== undefined) {
      setGameId(nightIndexResponseBody.gameId);
    }
  }, [nightIndexResponseBody]);

  const subscribeDoneNightActionOfAllPlayer: Subscribe = {
    path: `/topic/${gameId}`,
    callback: () => {
      window.location.href = '/talk';
    },
  };

  useWebSocket([subscribeDoneNightActionOfAllPlayer]);

  return nightIndexResponseBody === undefined ? (
    <Loading />
  ) : (
    <>
      <NightTemplate nightIndexResponseBody={nightIndexResponseBody} />
    </>
  );
};
