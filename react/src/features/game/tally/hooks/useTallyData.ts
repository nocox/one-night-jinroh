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
    const fetchTalkIndexResponseBodyAsync = async () => {
      const tallyIndexResponseBody = await fetchTallyIndexResponseBody();
      const { tallyResult, gameId, cos } = tallyIndexResponseBody;

      setGameId(gameId);
      setSelectedPlayers(tallyResult.selectedPlayers);
      setPlayersWithVoteCount(tallyResult.players);
      setIsPeaceful(tallyResult.peacefulFlag);
      setCos(cos);
    };

    void fetchTalkIndexResponseBodyAsync();
  }, []);

  const { hostFlag } = useGameIndex('tally', gameId);

  return {
    hostFlag,
    gameId,
    selectedPlayers,
    playersWithVoteCount,
    isPeaceful,
    cos,
  };
};
