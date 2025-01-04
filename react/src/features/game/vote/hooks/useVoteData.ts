import type React from 'react';
import { useEffect, useState } from 'react';
import { useQuery } from '@tanstack/react-query';
import { fetchGetWrapper } from '@/api';
import { useGameIndex } from '@/features/game/hooks/useGameIndex';
import { toGameParticipantsWithCoRole } from '@/features/game/service/gameParticipantService';
import type {
  GameParticipant,
  GameParticipantWithCoRole,
} from '@/features/game/type';
import { type VoteIndexRequestBody } from '@/features/game/vote/type';

type UseVoteData = () => {
  gameId: number | undefined;
  nightActLog: string | undefined;
  gameParticipantsWithCoRole: GameParticipantWithCoRole[] | undefined;
  canVotePlayers: GameParticipant[] | undefined;
  votingDestination: number | undefined;
  setVotingDestination: React.Dispatch<
    React.SetStateAction<number | undefined>
  >;
};

export const useVoteData: UseVoteData = () => {
  const [gameParticipantsWithCoRole, setGameParticipantWithCoRole] = useState<
    GameParticipantWithCoRole[] | undefined
  >();
  const [votingDestination, setVotingDestination] = useState<
    number | undefined
  >();

  const { data } = useQuery({
    queryKey: ['vote-index'],
    queryFn: async () => {
      return await fetchGetWrapper<VoteIndexRequestBody>('/vote-index');
    },
  });

  const gameId = data?.gameId;
  const cos = data?.cos;
  const canVotePlayers = data?.voteIndex.canVotePlayers;

  const {
    nightActLog,
    playerId,
    playerName,
    playerRole,
    otherPlayerList,
    hostFlag,
  } = useGameIndex('vote', gameId);

  useEffect(() => {
    if (
      hostFlag === undefined ||
      playerId === undefined ||
      playerName === undefined ||
      playerRole === undefined ||
      otherPlayerList === undefined ||
      cos === undefined
    )
      return;

    const gameParticipantsWithCoRole = toGameParticipantsWithCoRole(
      otherPlayerList,
      hostFlag,
      playerId,
      playerName,
      playerRole,
      cos,
    );
    setGameParticipantWithCoRole(gameParticipantsWithCoRole);
  }, [playerId, playerName, playerRole, otherPlayerList, cos, hostFlag]);

  return {
    gameId,
    nightActLog,
    gameParticipantsWithCoRole,
    canVotePlayers,
    votingDestination,
    setVotingDestination,
  };
};
