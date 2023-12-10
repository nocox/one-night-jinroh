import { useEffect, useState } from 'react';
import { type CoRole } from '../../type';
import { fetchTalkIndex } from '../api';

export const useTalkData = (): {
  gameId: number | undefined;
  nightActLog: string | undefined;
  hostFlg: boolean | undefined;
  cos: CoRole[] | undefined;
} => {
  const [gameId, setGameId] = useState<number | undefined>(undefined);
  const [nightActLog, setNightActLog] = useState<string | undefined>(undefined);
  const [hostFlg, setHostFlg] = useState<boolean | undefined>(undefined);
  const [cos, setCos] = useState<CoRole[] | undefined>(undefined);

  useEffect(() => {
    const fetchTalkIndexAsync = async () => {
      const talkIndexResponseBody = await fetchTalkIndex();
      setGameId(talkIndexResponseBody.gameId);
      setNightActLog(talkIndexResponseBody.gameIndex.nightActLog ?? undefined);
      setHostFlg(talkIndexResponseBody.gameIndex.hostFlag);
      setCos(talkIndexResponseBody.cos);
    };

    void fetchTalkIndexAsync();
  }, []);

  return {
    gameId,
    nightActLog,
    hostFlg,
    cos,
  };
};
