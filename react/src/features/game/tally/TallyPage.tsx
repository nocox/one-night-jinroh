import type React from 'react';
import { TallyTemplate } from './TallyTemplate';
import { TallyStartModal } from './components/TallyStartModal';
import { useTallyData } from './hooks/useTallyData';
import { fetchGetWrapper } from '@/api';
import { Loading } from '@/components';
import { useGameRule, useWebSocket } from '@/hooks';
import type { Subscribe } from '@/type';

export const TallyPage: React.FC = () => {
  const {
    gameId,
    hostFlag,
    selectedPlayers,
    playersWithVoteCount,
    isPeaceful,
    cos,
    isLoading,
  } = useTallyData();

  const gameRuleResult = useGameRule(gameId);
  const { roleList } = gameRuleResult.data ?? {};
  const subscribeResult: Subscribe = {
    path: `/topic/result/${gameId ?? ''}`,
    callback: () => {
      window.location.href = '/result';
    },
  };

  useWebSocket(gameId === undefined ? [] : [subscribeResult]);

  const handleClickResultButton = async () => {
    await fetchGetWrapper('/show-result');
  };

  return isLoading ||
    gameRuleResult.isLoading ||
    hostFlag === undefined ||
    selectedPlayers === undefined ||
    playersWithVoteCount === undefined ||
    isPeaceful === undefined ||
    cos === undefined ||
    roleList === undefined ? (
    <Loading />
  ) : (
    <>
      <TallyTemplate
        hostFlag={hostFlag}
        selectedPlayers={selectedPlayers}
        playersWithVoteCount={playersWithVoteCount}
        gameRuleList={roleList}
        isPeaceful={isPeaceful}
        cos={cos}
        handleClickResultButton={handleClickResultButton}
      />
      <TallyStartModal />
    </>
  );
};
