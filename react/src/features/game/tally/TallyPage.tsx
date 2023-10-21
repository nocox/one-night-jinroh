import { TallyTemplate } from './TallyTemplate';
import { fetchResult } from './api';
import { useTallyData } from './hooks/useTallyData';
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
  } = useTallyData();
  const { gameRuleList } = useGameRule(gameId);

  const subscribeResult: Subscribe = {
    path: `/topic/result/${gameId ?? ''}`,
    callback: () => {
      window.location.href = '/result';
    },
  };

  useWebSocket(gameId === undefined ? [] : [subscribeResult]);

  const handleClickResultButton = async () => {
    await fetchResult();
  };

  return hostFlag === undefined ||
    selectedPlayers === undefined ||
    playersWithVoteCount === undefined ||
    isPeaceful === undefined ||
    cos === undefined ||
    gameRuleList === undefined ? (
    <Loading />
  ) : (
    <TallyTemplate
      hostFlag={hostFlag}
      selectedPlayers={selectedPlayers}
      playersWithVoteCount={playersWithVoteCount}
      gameRuleList={gameRuleList}
      isPeaceful={isPeaceful}
      cos={cos}
      handleClickResultButton={handleClickResultButton}
    />
  );
};
