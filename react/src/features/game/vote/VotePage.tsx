import { VoteTemplate } from './VoteTemplate';
import { useVoteData } from './hooks/useVoteData';
import { Loading } from '@/components';
import { useGameRule } from '@/hooks';

export const VotePage: React.FC = () => {
  const { players, nightActLog, canVotePlayers, votingDestination } =
    useVoteData();
  const { gameRuleList } = useGameRule();

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
