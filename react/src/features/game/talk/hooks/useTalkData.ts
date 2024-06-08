import type React from 'react';
import { useCallback, useEffect, useState } from 'react';
import { fetchTalkIndex } from '../api';
import { UnexpectedError } from '@/features/error';
import { useGameIndex } from '@/features/game/hooks/useGameIndex';
import type {
  CoRole,
  GameParticipant,
  GameParticipantWithCoRole,
  RoleBean,
} from '@/features/game/type';

type UseTalkData = () => {
  gameId: number | undefined;
  nightActLog: string | undefined;
  hostFlag: boolean | undefined;
  gameParticipantsWithCoRole: GameParticipantWithCoRole[] | undefined;
  setGameParticipantsWithCoRole: React.Dispatch<
    React.SetStateAction<GameParticipantWithCoRole[] | undefined>
  >;
  getMyPlayer: () => GameParticipantWithCoRole;
};

export const useTalkData: UseTalkData = () => {
  const [gameId, setGameId] = useState<number | undefined>(undefined);
  const [cos, setCos] = useState<CoRole[] | undefined>(undefined);
  const [gameParticipantsWithCoRole, setGameParticipantsWithCoRole] = useState<
    GameParticipantWithCoRole[] | undefined
  >(undefined);

  // gameId と cos を取得
  useEffect(() => {
    void (async () => {
      try {
        const talkIndexResponseBody = await fetchTalkIndex();
        setGameId(talkIndexResponseBody.gameId);
        setCos(talkIndexResponseBody.cos);
      } catch (e) {
        console.error(e);
      }
    })();
  }, []);

  const {
    playerId,
    playerName,
    playerRole,
    otherPlayerList,
    nightActLog,
    hostFlag,
  } = useGameIndex('talk', gameId);

  // gameParticipantsWithCoRole を生成
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

    const gameParticipantsWithCoRole = toGameParticipantsWithCoRole(
      otherPlayerList,
      hostFlag,
      playerId,
      playerName,
      playerRole,
      cos,
    );

    setGameParticipantsWithCoRole(gameParticipantsWithCoRole);
  }, [playerId, playerName, playerRole, otherPlayerList, cos, hostFlag]);

  /**
   * 自分のプレイヤー情報を取得
   */
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

/**
 * 参加者にCoRoleを付与したGameParticipantWithCoRoleの配列を返す
 */
const toGameParticipantsWithCoRole = (
  otherPlayerList: GameParticipant[],
  hostFlag: boolean,
  playerId: number,
  playerName: string,
  playerRole: RoleBean,
  cos: CoRole[],
): GameParticipantWithCoRole[] => {
  return [
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
};
