import { useEffect, useState } from 'react';
import { TalkStartModal } from '@/features/game/talk/components/TalkStartModal';
import { TalkTemplate } from './TalkTemplate';
import { useTalkData } from './hooks/useTalkData';
import { Loading } from '@/components';
import { InvalidResponseBodyError, UnexpectedError } from '@/features/error';
import { useGameIndex } from '@/features/game/hooks/useGameIndex';
import type { Player } from '@/features/game/talk/type';
import { isCoBeans } from '@/features/game/type';
import type { CoRole, CoBeans } from '@/features/game/type';
import { useGameRule, useWebSocket } from '@/hooks';
import type { Subscribe } from '@/type';

export const TalkPage: React.FC = () => {
  const { gameId, nightActLog, hostFlg, cos } = useTalkData();
  const [players, setPlayers] = useState<Player[] | undefined>(undefined);

  const { gameRuleList } = useGameRule(gameId);
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

  const getMyPlayer = (): Player => {
    if (!players) {
      throw new UnexpectedError('players is undefined');
    }

    return players[0];
  };

  const subscribeEndTalk: Subscribe = {
    path: `/topic/end-talk/${gameId ?? ''}`,
    callback: () => {
      window.location.href = '/vote';
    },
  };

  const subscribeReceiveCo: Subscribe = {
    path: `/topic/receive-co/${gameId ?? ''}`,
    callback: (message) => {
      if (message === undefined) {
        throw new UnexpectedError(
          `Invalid response body: ${JSON.stringify(message)}`,
        );
      }

      const coBeans = JSON.parse(message.body) as CoBeans;

      if (!isCoBeans(coBeans)) {
        throw new InvalidResponseBodyError(`
          Invalid response body: ${JSON.stringify(coBeans)}`);
      }

      if (players === undefined) return;

      function findCo(coBeans: CoBeans, id: number): CoRole | undefined {
        const co = coBeans.coBeans.find((co) => co.id === id);

        return co;
      }

      const newPlayers = players.map((player) => {
        const co = findCo(coBeans, player.id);

        return co === undefined ? player : { ...player, co };
      });

      setPlayers(newPlayers);
    },
  };

  useWebSocket(
    gameId !== undefined ? [subscribeEndTalk, subscribeReceiveCo] : [],
  );

  return players === undefined ||
    hostFlg === undefined ||
    gameRuleList === undefined ? (
    <Loading />
  ) : (
    <>
      <TalkTemplate
        players={players}
        nightActLog={nightActLog}
        hostFlg={hostFlg}
        getMyPlayer={getMyPlayer}
        gameRuleList={gameRuleList}
      />
      <TalkStartModal />
    </>
  );
};
