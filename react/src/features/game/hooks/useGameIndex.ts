import { useEffect, useState } from 'react';
import { fetchGameIndex } from '@/features/game/api';
import type { GameParticipant, RoleBean } from '@/features/game/type';

export const useGameIndex = (): {
  hostFlag: boolean | undefined;
  nightActLog: string | undefined;
  otherPlayerList: GameParticipant[] | undefined;
  playerId: number | undefined;
  playerName: string | undefined;
  playerRole: RoleBean | undefined;
} => {
  const [hostFlag, setHostFlag] = useState<boolean | undefined>(undefined);
  const [nightActLog, setNightActLog] = useState<string | undefined>(undefined);
  const [otherPlayerList, setGameParticipantList] = useState<
    GameParticipant[] | undefined
  >(undefined);
  const [playerId, setPlayerId] = useState<number | undefined>(undefined);
  const [playerName, setPlayerName] = useState<string | undefined>(undefined);
  const [playerRole, setPlayerRole] = useState<RoleBean | undefined>(undefined);

  useEffect(() => {
    const fetchGameIndexAsync = async () => {
      const gameIndex = await fetchGameIndex();

      setHostFlag(gameIndex.hostFlag);
      setNightActLog(gameIndex.nightActLog ?? undefined);
      setGameParticipantList(gameIndex.otherPlayerList);
      setPlayerId(gameIndex.playerId);
      setPlayerName(gameIndex.playerName);
      setPlayerRole(gameIndex.playerRole);
    };

    void fetchGameIndexAsync();
  }, []);

  return {
    hostFlag,
    nightActLog,
    otherPlayerList,
    playerId,
    playerName,
    playerRole,
  };
};
