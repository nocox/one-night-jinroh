import type React from 'react';
import { ResultTemplate } from './ResultTemplate';
import { JudgeModal } from './components/JudgeModal/JudgeModal';
import { useResultData } from './hooks/useResultData';
import { fetchGetWrapper } from '@/api';
import { Loading } from '@/components';
import { useJudgeResult } from '@/features/game/result/hooks/useJudgeResult';
import { useWebSocket } from '@/hooks';
import { useModal } from '@/hooks/useModal';
import type { Subscribe } from '@/type';

export const ResultPage: React.FC = () => {
  const { data, isLoading } = useResultData();

  const { gameId, judge, hostFlg, participants, holidayRoles } = data ?? {};

  const judgeResult = useJudgeResult(judge);

  const { open, onCloseModal } = useModal(true);

  const subscribeReturnRoom: Subscribe = {
    path: `/topic/return-room/${gameId ?? ''}`,
    callback: () => {
      onCloseModal();
      window.location.href = '/room';
    },
  };

  const handleReturnRoom = async () => {
    await fetchGetWrapper('/return-room');
  };

  useWebSocket(gameId === undefined ? [] : [subscribeReturnRoom]);

  return isLoading ||
    judgeResult === undefined ||
    hostFlg === undefined ||
    participants === undefined ||
    holidayRoles === undefined ? (
    <Loading />
  ) : (
    <>
      <ResultTemplate
        judgeResult={judgeResult}
        hostFlag={hostFlg}
        participants={participants}
        holidayRoles={holidayRoles}
        handleReturnRoom={handleReturnRoom}
      />

      <JudgeModal
        open={open}
        onCloseModal={onCloseModal}
        judgeResult={judgeResult}
      />
    </>
  );
};
