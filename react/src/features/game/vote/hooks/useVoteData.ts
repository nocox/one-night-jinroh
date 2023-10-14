import { useEffect, useState } from 'react';
import { fetchVoteIndex } from '../api';
import type { Player } from '@/features/game/talk/type';
import type { OtherPlayer } from '@/features/game/type';

export const useVoteData = (): {
  gameId: number | undefined;
  nightActLog: string | undefined;
  players: Player[] | undefined;
  canVotePlayers: OtherPlayer[] | undefined;
  votingDestination: number | undefined;
} => {
  const [gameId, setGameId] = useState<number | undefined>();
  const [nightActLog, setNightActLog] = useState<string | undefined>();
  const [players, setPlayers] = useState<Player[] | undefined>();
  const [canVotePlayers, setCanVotePlayers] = useState<
    OtherPlayer[] | undefined
  >();
  const [votingDestination, setVotingDestination] = useState<
    number | undefined
  >();

  useEffect(() => {
    const fetchVoteIndexAsync = async () => {
      const voteIndexResponseBody = await fetchVoteIndex();
      const { gameIndex, voteIndex, cos } = voteIndexResponseBody;

      const players: Player[] = [
        {
          id: gameIndex.playerId,
          name: gameIndex.playerName,
          role: gameIndex.playerRole,
          co: cos.find((co) => co.id === gameIndex.playerId)!,
        },
        ...gameIndex.otherPlayerList.map((otherPlayer) => ({
          id: otherPlayer.id,
          name: otherPlayer.name,
          role: otherPlayer.role,
          co: cos.find((co) => co.id === otherPlayer.id)!,
        })),
      ];
      setGameId(voteIndexResponseBody.gameId);
      setPlayers(players);
      setNightActLog(gameIndex.nightActLog ?? undefined);
      setCanVotePlayers(voteIndex.canVotePlayers);
      setVotingDestination(voteIndex.votingDestination ?? undefined);
    };

    void fetchVoteIndexAsync();
  }, []);

  return { gameId, nightActLog, players, canVotePlayers, votingDestination };
};
