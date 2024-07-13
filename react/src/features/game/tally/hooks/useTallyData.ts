import { useEffect, useState } from 'react';
import { useGameIndex } from '@/features/game/hooks/useGameIndex';
import { fetchTallyIndexResponseBody } from '@/features/game/tally/api';
import type { GameParticipantWithVoteBean } from '@/features/game/tally/type';
import type { CoRole } from '@/features/game/type';

export const useTallyData = (): {
  hostFlag: boolean | undefined;
  gameId: number | undefined;
  selectedPlayers: GameParticipantWithVoteBean[] | undefined;
  playersWithVoteCount: GameParticipantWithVoteBean[] | undefined;
  isPeaceful: boolean | undefined;
  cos: CoRole[] | undefined;
} => {
  const [gameId, setGameId] = useState<number | undefined>();
  const [selectedPlayers, setSelectedPlayers] = useState<
    GameParticipantWithVoteBean[] | undefined
  >();
  const [playersWithVoteCount, setPlayersWithVoteCount] = useState<
    GameParticipantWithVoteBean[] | undefined
  >();
  const [isPeaceful, setIsPeaceful] = useState<boolean | undefined>(undefined);
  const [cos, setCos] = useState<CoRole[] | undefined>();

  useEffect(() => {
    const fetchTallyIndexResponseBodyAsync = async () => {
      const tallyIndexResponseBody = await fetchTallyIndexResponseBody();
      const { gameId } = tallyIndexResponseBody;
      setGameId(gameId);
    };
    void fetchTallyIndexResponseBodyAsync();
  }, []);

  const { hostFlag, otherPlayerList, playerId, playerName, playerRole } =
    useGameIndex('tally', gameId);

  useEffect(() => {
    if (
      hostFlag === undefined ||
      otherPlayerList === undefined ||
      playerId === undefined ||
      playerName === undefined ||
      playerRole === undefined
    ) {
      return;
    }

    const fetchTallyIndexResponseBodyAsync = async () => {
      const tallyIndexResponseBody = await fetchTallyIndexResponseBody();
      const { tallyResult, gameId, cos } = tallyIndexResponseBody;

      const playersWithVoteCount: GameParticipantWithVoteBean[] = [
        {
          id: playerId,
          name: playerName,
          role: playerRole,
          hostFlag,
          voteCount: tallyResult.players
            .filter((player) => player.id === playerId)
            .map((player) => player.voteCount)[0],
          isMyself: true,
        },
        ...otherPlayerList.map((player) => ({
          ...player,
          voteCount: tallyResult.players
            .filter((playerWithVote) => player.id === playerWithVote.id)
            .map((playerWithVote) => playerWithVote.voteCount)[0],
          isMyself: false,
        })),
      ];

      setGameId(gameId);
      setSelectedPlayers(tallyResult.selectedPlayers);
      setPlayersWithVoteCount(playersWithVoteCount);
      setIsPeaceful(tallyResult.peacefulFlag);
      setCos(cos);
    };

    void fetchTallyIndexResponseBodyAsync();
  }, [hostFlag, otherPlayerList, playerId, playerName, playerRole]);

  return {
    hostFlag,
    gameId,
    selectedPlayers,
    playersWithVoteCount,
    isPeaceful,
    cos,
  };
};
