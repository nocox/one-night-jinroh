import { useEffect, useState } from 'react';
import { fetchGameIndex } from '@/features/game/api';
import type {
  GameParticipant,
  RoleBean,
  FetchGameIndexParam,
} from '@/features/game/type';

export const useGameIndex = (
  param: FetchGameIndexParam,
  gameId: number | undefined,
): {
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
    if (gameId === undefined) {
      console.warn('gameId is undefined');

      return;
    }
    const fetchGameIndexAsync = async () => {
      const gameIndexResponse = await fetchGameIndex(param);

      switch (gameIndexResponse.type) {
        case 'GameIndex':
          setHostFlag(gameIndexResponse.hostFlag);
          setNightActLog(gameIndexResponse.nightActLog ?? '');
          setGameParticipantList(gameIndexResponse.otherPlayerList);
          setPlayerId(gameIndexResponse.playerId);
          setPlayerName(gameIndexResponse.playerName);
          setPlayerRole(gameIndexResponse.playerRole);
          break;
        case 'TermIsDifferent':
          window.location.href = '/' + gameIndexResponse.term;
          break;
        case 'NotStared':
          window.location.href = '/room';
          break;
      }
    };

    void fetchGameIndexAsync();
  }, [param, gameId]);

  return {
    hostFlag,
    nightActLog,
    otherPlayerList,
    playerId,
    playerName,
    playerRole,
  };
};
