import { useEffect, useState } from 'react';
import { fetchTalkIndex } from '../api';
import type { Player } from '../type';
import { UnexpectedError } from '@/error';

export const useTalkData = (): {
  gameId: number | undefined;
  players: Player[] | undefined;
  nightActLog: string | undefined;
  hostFlg: boolean | undefined;
  setPlayers: React.Dispatch<React.SetStateAction<Player[] | undefined>>;
  getMyPlayer: () => Player;
} => {
  const [players, setPlayers] = useState<Player[] | undefined>(undefined);
  const [gameId, setGameId] = useState<number | undefined>(undefined);
  const [nightActLog, setNightActLog] = useState<string | undefined>(undefined);
  const [hostFlg, setHostFlg] = useState<boolean | undefined>(undefined);

  useEffect(() => {
    const fetchTalkIndexAsync = async () => {
      const talkIndexResponseBody = await fetchTalkIndex();
      const { gameIndex, cos } = talkIndexResponseBody;
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
      setPlayers(players);
      setGameId(talkIndexResponseBody.gameId);
      setNightActLog(talkIndexResponseBody.gameIndex.nightActLog ?? undefined);
      setHostFlg(talkIndexResponseBody.gameIndex.hostFlag);
    };

    void fetchTalkIndexAsync();
  }, []);


  const getMyPlayer = (): Player => {
    if (!players) {
      throw new UnexpectedError('players is undefined');
    }

    return players[0];
  }

  return { gameId, players, nightActLog, hostFlg, setPlayers, getMyPlayer };
};
