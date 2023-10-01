import { TalkTemplate } from './TalkTemplate';
import { useTalkData } from './hooks/useTalkData';
import type { CoBeans } from './type';
import { isCoBeans } from './type';
import { Loading } from '@/components';
import { InvalidResponseBodyError, UnexpectedError } from '@/error';
import { useWebSocket } from '@/hooks';
import type { Subscribe } from '@/type';

export const TalkPage: React.FC = () => {
  const { gameId, players, getMyPlayer } = useTalkData();

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
      coBeans.coBeans.forEach((coBean) => {
        const player = players?.find((player) => {
          return player.id === coBean.id;
        });
        if (player !== undefined) {
          player.co.role = coBean.role;
        }
      });
    },
  };

  useWebSocket(
    gameId !== undefined ? [subscribeEndTalk, subscribeReceiveCo] : [],
  );

  return players === undefined ? (
    <Loading />
  ) : (
    <TalkTemplate players={players} getMyPlayer={getMyPlayer} />
  );
};
