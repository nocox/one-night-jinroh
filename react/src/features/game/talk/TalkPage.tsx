import type React from 'react';
import { TalkStartModal } from '@/features/game/talk/components/TalkStartModal';
import { TalkTemplate } from './TalkTemplate';
import { Loading } from '@/components';
import { UnexpectedError } from '@/features/error';
import { useGameIndex } from '@/features/game/hooks/useGameIndex';
import { useParticipantsWithCoRole } from '@/features/game/hooks/useParticipantsWithCoRole';
import { useRedirectByTerm } from '@/features/game/hooks/useRedirectByTerm';
import { useTalkIndex } from '@/features/game/talk/hooks/useTalkIndex';
import type { CoRole, CoBeans } from '@/features/game/type';
import { useGameRule, useWebSocket } from '@/hooks';
import type { Subscribe } from '@/type';

export const TalkPage: React.FC = () => {
  const talkIndexResult = useTalkIndex();
  const { gameId, cos } = talkIndexResult.data ?? {};

  const gameIndexResult = useGameIndex('talk', gameId);
  const { type } = gameIndexResult.data ?? {};

  useRedirectByTerm(type, 'talk');

  const gameRuleResult = useGameRule(gameId);
  const { roleList } = gameRuleResult.data ?? {};

  const {
    gameParticipantsWithCoRole,
    getMyPlayer,
    setGameParticipantsWithCoRole,
  } = useParticipantsWithCoRole(gameIndexResult.data, cos);

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

  return gameIndexResult.isLoading ||
    gameIndexResult.data === undefined ||
    talkIndexResult.isLoading ||
    gameParticipantsWithCoRole === undefined ||
    roleList === undefined ||
    gameIndexResult.data.type !== 'GameIndex' ? ( // ATTENTION: GameIndexのみを受け取ることができるコンポーネントを切り出してもいいかもしれない
    <Loading />
  ) : (
    <>
      <TalkTemplate
        players={gameParticipantsWithCoRole}
        nightActLog={gameIndexResult.data.nightActLog}
        hostFlg={gameIndexResult.data.hostFlag}
        getMyPlayer={getMyPlayer}
        gameRuleList={roleList}
      />
      <TalkStartModal />
    </>
  );
};
