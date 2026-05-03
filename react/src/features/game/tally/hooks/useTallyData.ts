import { useEffect, useState } from 'react';
import { useQuery } from '@tanstack/react-query';
import { fetchGetWrapper } from '@/api';
import { useGameIndex } from '@/features/game/hooks/useGameIndex';
import {
  type GameParticipantWithVoteBean,
  type TallyIndexResponseBody,
} from '@/features/game/tally/type';
import type { CoRole } from '@/features/game/type';
import { isGameIndex } from '@/features/game/type';

type UseTallyDataResult = {
  hostFlag: boolean | undefined;
  gameId: number | undefined;
  selectedPlayers: GameParticipantWithVoteBean[] | undefined;
  playersWithVoteCount: GameParticipantWithVoteBean[] | undefined;
  isPeaceful: boolean | undefined;
  cos: CoRole[] | undefined;
  isLoading: boolean;
};

export const useTallyData = (): UseTallyDataResult => {
  const [playersWithVoteCount, setPlayersWithVoteCount] = useState<
    GameParticipantWithVoteBean[] | undefined
  >();

  const { data, isLoading } = useQuery({
    queryKey: ['tally-index'],
    queryFn: async () =>
      await fetchGetWrapper<TallyIndexResponseBody>('/tally-index'),
  });

  const gameId = data?.gameId;
  const cos = data?.cos;
  const tallyResult = data?.tallyResult;
  const selectedPlayers = tallyResult?.selectedPlayers;
  const isPeaceful = tallyResult?.peacefulFlag;

  const gameIndexResult = useGameIndex('tally', gameId);
  const gameIndex = gameIndexResult.data;

  useEffect(() => {
    if (!isGameIndex(gameIndex) || tallyResult === undefined) {
      return;
    }

    const playersWithVoteCount: GameParticipantWithVoteBean[] = [
      {
        id: gameIndex.playerId,
        name: gameIndex.playerName,
        role: gameIndex.playerRole,
        hostFlag: gameIndex.hostFlag,
        voteCount: tallyResult.players
          .filter((player) => player.id === gameIndex.playerId)
          .map((player) => player.voteCount)[0],
        isMyself: true,
      },
      ...gameIndex.otherPlayerList.map((player) => ({
        ...player,
        voteCount: tallyResult.players
          .filter((playerWithVote) => player.id === playerWithVote.id)
          .map((playerWithVote) => playerWithVote.voteCount)[0],
        isMyself: false,
      })),
    ];

    setPlayersWithVoteCount(playersWithVoteCount);
  }, [gameIndex, tallyResult]);

  return {
    hostFlag: isGameIndex(gameIndex) ? gameIndex.hostFlag : undefined,
    gameId,
    selectedPlayers,
    playersWithVoteCount,
    isPeaceful,
    cos,
    isLoading: isLoading || gameIndexResult.isLoading,
  };
};
