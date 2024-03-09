import { useCallback, useEffect, useState } from 'react';
import { fetchTalkIndex } from '../api';
import { UnexpectedError } from '@/features/error';
import { useGameIndex } from '@/features/game/hooks/useGameIndex';
import type { CoRole, GameParticipantWithCoRole } from '@/features/game/type';

export const useTalkData = (): {
  gameId: number | undefined;
  nightActLog: string | undefined;
  hostFlag: boolean | undefined;
  gameParticipantsWithCoRole: GameParticipantWithCoRole[] | undefined;
  setGameParticipantsWithCoRole: React.Dispatch<
    React.SetStateAction<GameParticipantWithCoRole[] | undefined>
  >;
  getMyPlayer: () => GameParticipantWithCoRole;
} => {
  const [gameId, setGameId] = useState<number | undefined>(undefined);
  const [cos, setCos] = useState<CoRole[] | undefined>(undefined);
  const [gameParticipantsWithCoRole, setGameParticipantsWithCoRole] = useState<
    GameParticipantWithCoRole[] | undefined
  >(undefined);

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
      hostFlag === undefined ||
      playerId === undefined ||
      playerName === undefined ||
      playerRole === undefined ||
      otherPlayerList === undefined ||
      cos === undefined
    )
      return;

    const gameParticipantsWithCoRole: GameParticipantWithCoRole[] = [
      {
        hostFlag,
        id: playerId,
        name: playerName,
        role: playerRole,
        co: cos.find((co) => co.id === playerId)!,
      },
      ...otherPlayerList.map((otherPlayer) => ({
        hostFlag: otherPlayer.hostFlag,
        id: otherPlayer.id,
        name: otherPlayer.name,
        role: otherPlayer.role,
        co: cos.find((co) => co.id === otherPlayer.id)!,
      })),
    ];
    setGameParticipantsWithCoRole(gameParticipantsWithCoRole);
  }, [playerId, playerName, playerRole, otherPlayerList, cos, hostFlag]);

  const getMyPlayer = useCallback((): GameParticipantWithCoRole => {
    if (!gameParticipantsWithCoRole) {
      throw new UnexpectedError('players is undefined');
    }

    return gameParticipantsWithCoRole[0];
  }, [gameParticipantsWithCoRole]);

  return {
    gameId,
    nightActLog,
    hostFlag,
    gameParticipantsWithCoRole,
    setGameParticipantsWithCoRole,
    getMyPlayer,
  };
};
