import { TalkStartModal } from '@/features/game/talk/components/TalkStartModal';
import { TalkTemplate } from './TalkTemplate';
import { useTalkData } from './hooks/useTalkData';
import { Loading } from '@/components';
import { InvalidResponseBodyError, UnexpectedError } from '@/features/error';
import { isCoBeans } from '@/features/game/type';
import type { CoRole, CoBeans } from '@/features/game/type';
import { useGameRule, useWebSocket } from '@/hooks';
import type { Subscribe } from '@/type';

export const TalkPage: React.FC = () => {
  const {
    gameId,
    nightActLog,
    hostFlag,
    gameParticipantsWithCoRole,
    setGameParticipantsWithCoRole,
    getMyPlayer,
  } = useTalkData();

  const { gameRuleList } = useGameRule(gameId);

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

      if (gameParticipantsWithCoRole === undefined) return;

      function findCo(coBeans: CoBeans, id: number): CoRole | undefined {
        const co = coBeans.coBeans.find((co) => co.id === id);

        return co;
      }

      const newPlayers = gameParticipantsWithCoRole.map((player) => {
        const co = findCo(coBeans, player.id);

        return co === undefined ? player : { ...player, co };
      });

      setGameParticipantsWithCoRole(newPlayers);
    },
  };

  useWebSocket(
    gameId !== undefined ? [subscribeEndTalk, subscribeReceiveCo] : [],
  );

  return gameParticipantsWithCoRole === undefined ||
    hostFlag === undefined ||
    gameRuleList === undefined ? (
    <Loading />
  ) : (
    <>
      <TalkTemplate
        players={gameParticipantsWithCoRole}
        nightActLog={nightActLog}
        hostFlg={hostFlag}
        getMyPlayer={getMyPlayer}
        gameRuleList={gameRuleList}
      />
      <TalkStartModal />
    </>
  );
};
