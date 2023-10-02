import { TalkTemplate } from './TalkTemplate';
import { useTalkData } from './hooks/useTalkData';
import type { Co, CoBeans } from './type';
import { isCoBeans } from './type';
import { Loading } from '@/components';
import { InvalidResponseBodyError, UnexpectedError } from '@/error';
import { useWebSocket } from '@/hooks';
import type { Subscribe } from '@/type';

export const TalkPage: React.FC = () => {
  const { gameId, players, nightActLog, hostFlg, setPlayers, getMyPlayer } =
    useTalkData();

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

      function findCo(coBeans: CoBeans, id: number): Co | undefined {
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

  return players === undefined || hostFlg === undefined ? (
    <Loading />
  ) : (
    <TalkTemplate
      players={players}
      nightActLog={nightActLog}
      hostFlg={hostFlg}
      getMyPlayer={getMyPlayer}
    />
  );
};
