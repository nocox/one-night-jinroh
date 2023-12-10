import { useCallback, useEffect, useState } from 'react';
import { fetchTalkIndex } from '../api';
import { UnexpectedError } from '@/features/error';
import { useGameIndex } from '@/features/game/hooks/useGameIndex';
import type { Player } from '@/features/game/talk/type';
import type { CoRole } from '@/features/game/type';

export const useTalkData = (): {
  gameId: number | undefined;
  nightActLog: string | undefined;
  hostFlag: boolean | undefined;
  players: Player[] | undefined;
  setPlayers: React.Dispatch<React.SetStateAction<Player[] | undefined>>;
  getMyPlayer: () => Player;
} => {
  const [gameId, setGameId] = useState<number | undefined>(undefined);
  const [cos, setCos] = useState<CoRole[] | undefined>(undefined);
  const [players, setPlayers] = useState<Player[] | undefined>(undefined);

  useEffect(() => {
    const fetchTalkIndexAsync = async () => {
      const talkIndexResponseBody = await fetchTalkIndex();
      setGameId(talkIndexResponseBody.gameId);
      setCos(talkIndexResponseBody.cos);
    };

    void fetchTalkIndexAsync();
  }, []);

  const {
    playerId,
    playerName,
    playerRole,
    otherPlayerList,
    nightActLog,
    hostFlag,
  } = useGameIndex('talk', gameId);

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
    hostFlag,
    players,
    setPlayers,
    getMyPlayer,
  };
};
