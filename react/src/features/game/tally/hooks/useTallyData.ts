import { useEffect, useState } from 'react';
import { useQuery } from '@tanstack/react-query';
import { fetchGetWrapper } from '@/api';
import { useGameIndex } from '@/features/game/hooks/useGameIndex';
import {
  type GameParticipantWithVoteBean,
  type TallyIndexResponseBody,
} from '@/features/game/tally/type';
import type { CoRole } from '@/features/game/type';

export const useTallyData = (): {
  hostFlag: boolean | undefined;
  gameId: number | undefined;
  selectedPlayers: GameParticipantWithVoteBean[] | undefined;
  playersWithVoteCount: GameParticipantWithVoteBean[] | undefined;
  isPeaceful: boolean | undefined;
  cos: CoRole[] | undefined;
} => {
  const [playersWithVoteCount, setPlayersWithVoteCount] = useState<
    GameParticipantWithVoteBean[] | undefined
  >();

  const { data } = useQuery({
    queryKey: ['talk-index'],
    queryFn: async () =>
      await fetchGetWrapper<TallyIndexResponseBody>('/tally-index'),
  });

  const gameId = data?.gameId;
  const cos = data?.cos;
  const tallyResult = data?.tallyResult;
  const selectedPlayers = tallyResult?.selectedPlayers;
  const isPeaceful = tallyResult?.peacefulFlag;

  const { hostFlag, otherPlayerList, playerId, playerName, playerRole } =
    useGameIndex('tally', gameId);

  useEffect(() => {
    if (
      hostFlag === undefined ||
      otherPlayerList === undefined ||
      playerId === undefined ||
      playerName === undefined ||
      playerRole === undefined ||
      tallyResult === undefined
    ) {
      return;
    }

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

    setPlayersWithVoteCount(playersWithVoteCount);
  }, [
    hostFlag,
    otherPlayerList,
    playerId,
    playerName,
    playerRole,
    tallyResult,
  ]);

  return {
    hostFlag,
    gameId,
    selectedPlayers,
    playersWithVoteCount,
    isPeaceful,
    cos,
  };
};
