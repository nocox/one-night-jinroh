import { VoteTemplate } from './VoteTemplate';
import { useVoteData } from './hooks/useVoteData';
import { Loading } from '@/components';
import { useGameRule, useWebSocket } from '@/hooks';
import type { Subscribe } from '@/type';

export const VotePage: React.FC = () => {
  const { gameId, players, nightActLog, canVotePlayers, votingDestination } =
    useVoteData();
  const { gameRuleList } = useGameRule();

  const subscribeDoneTally: Subscribe = {
    path: `/topic/done-tally/${gameId ?? ''}`,
    callback: () => {
      window.location.href = '/tally';
    },
  };

  useWebSocket(gameId !== undefined ? [subscribeDoneTally] : []);

  return players === undefined ||
    gameRuleList === undefined ||
    canVotePlayers === undefined ? (
    <Loading />
  ) : (
    <VoteTemplate
      nightActLog={nightActLog}
      players={players}
      gameRuleList={gameRuleList}
      canVotePlayers={canVotePlayers}
      votingDestination={votingDestination}
    />
  );
};