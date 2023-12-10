import { useCallback, useEffect, useState } from 'react';
import { type CoRole } from '../../type';
import { fetchTalkIndex } from '../api';
import { UnexpectedError } from '@/features/error';
import { useGameIndex } from '@/features/game/hooks/useGameIndex';
import type { Player } from '@/features/game/talk/type';

export const useTalkData = (): {
  gameId: number | undefined;
  nightActLog: string | undefined;
  hostFlg: boolean | undefined;
  players: Player[] | undefined;
  setPlayers: React.Dispatch<React.SetStateAction<Player[] | undefined>>;
  getMyPlayer: () => Player;
} => {
  const [gameId, setGameId] = useState<number | undefined>(undefined);
  const [nightActLog, setNightActLog] = useState<string | undefined>(undefined);
  const [hostFlg, setHostFlg] = useState<boolean | undefined>(undefined);
  const [cos, setCos] = useState<CoRole[] | undefined>(undefined);
  const [players, setPlayers] = useState<Player[] | undefined>(undefined);

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

  const { playerId, playerName, playerRole, otherPlayerList } = useGameIndex(
    'talk',
    gameId,
  );

  useEffect(() => {
    if (
      playerId === undefined ||
      playerName === undefined ||
      playerRole === undefined ||
      otherPlayerList === undefined ||
      cos === undefined
    )
      return;

    const players: Player[] = [
      {
        id: playerId,
        name: playerName,
        role: playerRole,
        co: cos.find((co) => co.id === playerId)!,
      },
      ...otherPlayerList.map((otherPlayer) => ({
        id: otherPlayer.id,
        name: otherPlayer.name,
        role: otherPlayer.role,
        co: cos.find((co) => co.id === otherPlayer.id)!,
      })),
    ];
    setPlayers(players);
  }, [playerId, playerName, playerRole, otherPlayerList, cos]);

  const getMyPlayer = useCallback((): Player => {
    if (!players) {
      throw new UnexpectedError('players is undefined');
    }

    return players[0];
  }, [players]);

  return {
    gameId,
    nightActLog,
    hostFlg,
    players,
    setPlayers,
    getMyPlayer,
  };
};
