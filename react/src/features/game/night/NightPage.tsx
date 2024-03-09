import { NightTemplate } from './NightTemplate';
import { useNightData } from './hooks/useNightData';
import { Loading } from '@/components';
import { useWebSocket } from '@/hooks';
import type { Subscribe } from '@/type';

export const NightPage: React.FC = () => {
  const { gameId, gameIndex, doneNightAct } = useNightData();

  const subscribeDoneNightActionOfAllPlayer: Subscribe = {
    path: `/topic/${gameId ?? ''}`,
    callback: () => {
      window.location.href = '/talk';
    },
  };

  useWebSocket(
    gameId !== undefined ? [subscribeDoneNightActionOfAllPlayer] : [],
  );

  return gameIndex === undefined || doneNightAct === undefined ? (
    <Loading />
  ) : (
    <>
      <NightTemplate gameIndex={gameIndex} doneNightAct={doneNightAct} />
    </>
  );
};
