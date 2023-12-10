import { useEffect, useState } from 'react';
import { fetchVoteIndex } from '../api';
import { useGameIndex } from '@/features/game/hooks/useGameIndex';
import type {
  CoRole,
  GameParticipant,
  GameParticipantWithCoRole,
} from '@/features/game/type';

export const useVoteData = (): {
  gameId: number | undefined;
  nightActLog: string | undefined;
  gameParticipantsWithCoRole: GameParticipantWithCoRole[] | undefined;
  canVotePlayers: GameParticipant[] | undefined;
  votingDestination: number | undefined;
  setVotingDestination: React.Dispatch<
    React.SetStateAction<number | undefined>
  >;
} => {
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
    const fetchVoteIndexAsync = async () => {
      const voteIndexResponseBody = await fetchVoteIndex();
      const { voteIndex } = voteIndexResponseBody;

      setCos(voteIndexResponseBody.cos);
      setGameId(voteIndexResponseBody.gameId);
      setCanVotePlayers(voteIndex.canVotePlayers);
      setVotingDestination(voteIndex.votingDestination ?? undefined);
    };

    void fetchVoteIndexAsync();
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

    const gameParticipantsWithCoRole: GameParticipantWithCoRole[] = [
      {
        hostFlag,
        id: playerId,
        name: playerName,
        role: playerRole,
        co: cos.find((co) => co.id === playerId)!,
      },
      ...otherPlayerList.map((otherPlayer) => ({
        hostFlag: otherPlayer.hostFlag,
        id: otherPlayer.id,
        name: otherPlayer.name,
        role: otherPlayer.role,
        co: cos.find((co) => co.id === otherPlayer.id)!,
      })),
    ];
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
