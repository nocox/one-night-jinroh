import { useCallback, useEffect, useState } from 'react';
import { UnexpectedError } from '@/features/error';
import { toGameParticipantsWithCoRole } from '@/features/game/service/gameParticipantService';
import type {
  CoRole,
  GameIndexResponse,
  GameParticipantWithCoRole,
} from '@/features/game/type';

export const useParticipantsWithCoRole = (
  gameIndexResponse: GameIndexResponse | undefined,
  cos: CoRole[] | undefined,
): {
  gameParticipantsWithCoRole: GameParticipantWithCoRole[] | undefined;
  getMyPlayer: () => GameParticipantWithCoRole;
  setGameParticipantsWithCoRole: (players: GameParticipantWithCoRole[]) => void;
} => {
  const [gameParticipantsWithCoRole, setGameParticipantsWithCoRole] = useState<
    GameParticipantWithCoRole[] | undefined
  >(undefined);

  // gameParticipantsWithCoRole を生成
  useEffect(() => {
    if (gameIndexResponse === undefined || cos === undefined) {
      return;
    }

    if (gameIndexResponse.type !== 'GameIndex') {
      return;
    }

    const gameIndex = gameIndexResponse;

    const gameParticipantsWithCoRole = toGameParticipantsWithCoRole(
      gameIndex.otherPlayerList,
      gameIndex.hostFlag,
      gameIndex.playerId,
      gameIndex.playerName,
      gameIndex.playerRole,
      cos,
    );

    setGameParticipantsWithCoRole(gameParticipantsWithCoRole);
  }, [gameIndexResponse, cos]);

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
    gameParticipantsWithCoRole,
    getMyPlayer,
    setGameParticipantsWithCoRole,
  };
};
