import type React from 'react';
import { useEffect, useState } from 'react';
import { VoteTemplate } from './VoteTemplate';
import { VoteStartModal } from './components/VoteStartModal';
import { useVoteData } from './hooks/useVoteData';
import { Loading } from '@/components';
import { useGameIndex } from '@/features/game/hooks/useGameIndex';
import { useParticipantsWithCoRole } from '@/features/game/hooks/useParticipantsWithCoRole';
import { useRedirectByTerm } from '@/features/game/hooks/useRedirectByTerm';
import { isGameIndex } from '@/features/game/type';
import { useGameRule, useWebSocket } from '@/hooks';
import type { Subscribe } from '@/type';

export const VotePage: React.FC = () => {
  const [votingDestination, setVotingDestination] = useState<number | null>(
    null,
  );

  const voteDataResult = useVoteData();
  const { gameId, voteIndex, cos } = voteDataResult.data ?? {};

  const { canVotePlayers, votingDestination: savedVotingDestination } =
    voteIndex ?? {};

  useEffect(() => {
    if (savedVotingDestination !== undefined) {
      setVotingDestination(savedVotingDestination);
    }
  }, [savedVotingDestination]);

  const gameIndexResponse = useGameIndex('vote', gameId);
  useRedirectByTerm(gameIndexResponse.data?.type, 'vote');
  const { gameParticipantsWithCoRole } = useParticipantsWithCoRole(
    gameIndexResponse.data,
    cos,
  );

  const gameRuleResult = useGameRule(gameId);
  const { roleList } = gameRuleResult.data ?? {};

  const subscribeDoneTally: Subscribe = {
    path: `/topic/done-tally/${voteDataResult.data?.gameId ?? ''}`,
    callback: () => {
      window.location.href = '/tally';
    },
  };

  useWebSocket(
    voteDataResult.data?.gameId !== undefined ? [subscribeDoneTally] : [],
  );

  return gameParticipantsWithCoRole === undefined ||
    roleList === undefined ||
    canVotePlayers === undefined ||
    voteDataResult.data?.voteIndex.canVotePlayers === undefined ||
    gameIndexResponse.data === undefined ? (
    <Loading />
  ) : (
    isGameIndex(gameIndexResponse.data) && (
      <>
        <VoteTemplate
          nightActLog={gameIndexResponse.data.nightActLog}
          players={gameParticipantsWithCoRole}
          gameRuleList={roleList}
          canVotePlayers={canVotePlayers}
          votingDestination={votingDestination}
          setVottingDestination={setVotingDestination}
        />
        <VoteStartModal />
      </>
    )
  );
};
