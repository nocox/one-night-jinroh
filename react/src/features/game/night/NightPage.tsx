import { NightTemplate } from './NightTemplate';
import { useNightData } from './hooks/useNightData';
import { Loading } from '@/components';
import { useGameIndex } from '@/features/game/hooks/useGameIndex';
import { useWebSocket } from '@/hooks';
import type { Subscribe } from '@/type';

export const NightPage: React.FC = () => {
  const { gameId, doneNightAct } = useNightData();
  const { playerName, playerRole, otherPlayerList } = useGameIndex(
    'night',
    gameId,
  );

  const subscribeDoneNightActionOfAllPlayer: Subscribe = {
    path: `/topic/${gameId ?? ''}`,
    callback: () => {
      window.location.href = '/talk';
    },
  };

  useWebSocket(
    gameId !== undefined ? [subscribeDoneNightActionOfAllPlayer] : [],
  );

  return playerName === undefined ||
    playerRole === undefined ||
    otherPlayerList === undefined ||
    doneNightAct === undefined ? (
    <Loading />
  ) : (
    <>
      <NightTemplate
        playerName={playerName}
        playerRole={playerRole}
        otherPlayerList={otherPlayerList}
        doneNightAct={doneNightAct}
      />
    </>
  );
};
