import { VoteTemplate } from './VoteTemplate';
import { VoteStartModal } from './components/VoteStartModal';
import { useVoteData } from './hooks/useVoteData';
import { Loading } from '@/components';
import { useGameRule, useWebSocket } from '@/hooks';
import type { Subscribe } from '@/type';

export const VotePage: React.FC = () => {
  const {
    gameId,
    gameParticipantsWithCoRole,
    nightActLog,
    canVotePlayers,
    votingDestination,
    setVotingDestination,
  } = useVoteData();

  const { gameRuleList } = useGameRule(gameId);

  const subscribeDoneTally: Subscribe = {
    path: `/topic/done-tally/${gameId ?? ''}`,
    callback: () => {
      window.location.href = '/tally';
    },
  };

  useWebSocket(gameId !== undefined ? [subscribeDoneTally] : []);

  return gameParticipantsWithCoRole === undefined ||
    gameRuleList === undefined ||
    canVotePlayers === undefined ? (
    <Loading />
  ) : (
    <>
      <VoteTemplate
        nightActLog={nightActLog}
        players={gameParticipantsWithCoRole}
        gameRuleList={gameRuleList}
        canVotePlayers={canVotePlayers}
        votingDestination={votingDestination}
        setVottingDestination={setVotingDestination}
      />
      <VoteStartModal />
    </>
  );
};
