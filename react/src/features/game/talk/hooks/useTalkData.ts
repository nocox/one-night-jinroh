import type React from 'react';
import { useCallback, useEffect, useState } from 'react';
import { fetchGetWrapper } from '@/api';
import { UnexpectedError } from '@/features/error';
import { useGameIndex } from '@/features/game/hooks/useGameIndex';
import { toGameParticipantsWithCoRole } from '@/features/game/service/gameParticipantService';
import { type TalkIndexResponseBody } from '@/features/game/talk/type';
import type { CoRole, GameParticipantWithCoRole } from '@/features/game/type';

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
        const talkIndexResponseBody =
          await fetchGetWrapper<TalkIndexResponseBody>('/talk-index');
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
