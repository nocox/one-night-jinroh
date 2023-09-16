import { useEffect, useState } from 'react';
import { fetchNightIndex } from '../api';
import type { NightIndexResponseBody } from '../type';
import { type GameIndex } from '@/features/game/type';

export const useNightData = (): NightIndexResponseBody => {
  const [doneNightAct, setDoneNightAct] = useState<boolean>(false);
  const [gameId, setGameId] = useState<number>(-1);
  const [gameIndex, setGameIndex] = useState<GameIndex>({
    hostFlag: false,
    nightActLog: null,
    otherPlayerList: [],
    playerId: -1,
    playerName: '',
    playerRole: {
      roleId: -1,
      roleName: '不明',
    },
  });

  useEffect(() => {
    const fetchNightIndexAsync = async () => {
      const nightIndexResponseBody = await fetchNightIndex();
      setDoneNightAct(nightIndexResponseBody.doneNightAct);
      setGameId(nightIndexResponseBody.gameId);
      setGameIndex(nightIndexResponseBody.gameIndex);
    };
    void fetchNightIndexAsync();
  }, []);

  return { doneNightAct, gameId, gameIndex };
};
