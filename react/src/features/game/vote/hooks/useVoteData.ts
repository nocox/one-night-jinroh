import type React from 'react';
import { useEffect, useState } from 'react';
import { fetchVoteIndex } from '../api';
import { useGameIndex } from '@/features/game/hooks/useGameIndex';
import { toGameParticipantsWithCoRole } from '@/features/game/service/gameParticipantService';
import type {
  CoRole,
  GameParticipant,
  GameParticipantWithCoRole,
} from '@/features/game/type';

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
  const [gameId, setGameId] = useState<number | undefined>();
  const [gameParticipantsWithCoRole, setGameParticipantWithCoRole] = useState<
    GameParticipantWithCoRole[] | undefined
  >();
  const [cos, setCos] = useState<CoRole[] | undefined>(undefined);
  const [canVotePlayers, setCanVotePlayers] = useState<
    GameParticipant[] | undefined
  >();
  const [votingDestination, setVotingDestination] = useState<
    number | undefined
  >();

  useEffect(() => {
    void (async () => {
      const voteIndexResponseBody = await fetchVoteIndex();
      const { voteIndex } = voteIndexResponseBody;

      setCos(voteIndexResponseBody.cos);
      setGameId(voteIndexResponseBody.gameId);
      setCanVotePlayers(voteIndex.canVotePlayers);
      setVotingDestination(voteIndex.votingDestination ?? undefined);
    })();
  }, []);

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
