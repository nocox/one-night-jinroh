import type React from 'react';
import { NightTemplate } from './NightTemplate';
import { useNightIndex } from './hooks/useNightIndex';
import { Loading } from '@/components';
import { useGameIndex } from '@/features/game/hooks/useGameIndex';
import { useRedirectByTerm } from '@/features/game/hooks/useRedirectByTerm';
import { isGameIndex } from '@/features/game/type';
import { useWebSocket } from '@/hooks';
import type { Subscribe } from '@/type';

export const NightPage: React.FC = () => {
  const nightIndexResult = useNightIndex();
  const { gameId } = nightIndexResult.data ?? {};

  const gameIndexResult = useGameIndex('night', gameId);

  const gameIndexResultType = gameIndexResult.data?.type;
  useRedirectByTerm(gameIndexResultType, 'night');

  const subscribeDoneNightActionOfAllPlayer: Subscribe = {
    path: `/topic/${gameId ?? ''}`,
    callback: () => {
      window.location.href = '/talk';
    },
  };

  useWebSocket(
    gameId !== undefined ? [subscribeDoneNightActionOfAllPlayer] : [],
  );

  const isLoadingNightIndex = nightIndexResult.isLoading;
  const isLoadingGameIndex = gameIndexResult.isLoading;

  return isLoadingNightIndex ||
    isLoadingGameIndex ||
    nightIndexResult.data === undefined ? (
    <Loading />
  ) : (
    isGameIndex(gameIndexResult.data) && (
      <NightTemplate
        playerName={gameIndexResult.data.playerName}
        playerRole={gameIndexResult.data.playerRole}
        otherPlayerList={gameIndexResult.data.otherPlayerList}
        doneNightAct={nightIndexResult.data?.doneNightAct}
      />
    )
  );
};
