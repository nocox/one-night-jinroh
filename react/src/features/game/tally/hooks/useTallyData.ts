import { useEffect, useState } from 'react';
import { fetchTallyIndexResponseBody } from '@/features/game/tally/api';
import type { GameParticipantWithVoteBean } from '@/features/game/tally/type';
import type { CoBean } from '@/features/game/type';

export const useTallyData = (): {
  hostFlag: boolean | undefined;
  gameId: number | undefined;
  selectedPlayers: GameParticipantWithVoteBean[] | undefined;
  playersWithVoteCount: GameParticipantWithVoteBean[] | undefined;
  isPeaceful: boolean | undefined;
  cos: CoBean[] | undefined;
} => {
  const [hostFlag, setHostFlag] = useState<boolean | undefined>();
  const [gameId, setGameId] = useState<number | undefined>();
  const [selectedPlayers, setSelectedPlayers] = useState<
    GameParticipantWithVoteBean[] | undefined
  >();
  const [playersWithVoteCount, setPlayersWithVoteCount] = useState<
    GameParticipantWithVoteBean[] | undefined
  >();
  const [isPeaceful, setIsPeaceful] = useState<boolean | undefined>(undefined);
  const [cos, setCos] = useState<CoBean[] | undefined>();

  useEffect(() => {
    const fetchTalkIndexResponseBodyAsync = async () => {
      const tallyIndexResponseBody = await fetchTallyIndexResponseBody();
      const { tallyResult, gameId, gameIndex, cos } = tallyIndexResponseBody;

      setHostFlag(gameIndex.hostFlag);
      setGameId(gameId);
      setSelectedPlayers(tallyResult.selectedPlayers);
      setPlayersWithVoteCount(tallyResult.players);
      setIsPeaceful(tallyResult.peacefulFlag);
      setCos(cos);
    };

    void fetchTalkIndexResponseBodyAsync();
  }, []);

  return {
    hostFlag,
    gameId,
    selectedPlayers,
    playersWithVoteCount,
    isPeaceful,
    cos,
  };
};
