import type React from 'react';
import { ResultTemplate } from './ResultTemplate';
import { JudgeModal } from './components/JudgeModal/JudgeModal';
import { useResultData } from './hooks/useResultData';
import {fetchGetWrapper, fetchPostWrapper} from '@/api';
import { Loading } from '@/components';
import { useWebSocket } from '@/hooks';
import { useModal } from '@/hooks/useModal';
import type { Subscribe } from '@/type';

export const ResultPage: React.FC = () => {
  const { gameId, hostFlag, judgeResult, participants, holidayRoles } =
    useResultData();

  const { open, onCloseModal } = useModal(true);

  const subscribeReturnRoom: Subscribe = {
    path: `/topic/return-room/${gameId ?? ''}`,
    callback: async() => {
      onCloseModal();
      await fetchPostWrapper('/leave-game');
      window.location.href = '/room';
    },
  };

  const handleReturnRoom = async () => {
    await fetchGetWrapper('/return-room');
  };

  useWebSocket(gameId === undefined ? [] : [subscribeReturnRoom]);

  return hostFlag === undefined ||
    judgeResult === undefined ||
    participants === undefined ||
    holidayRoles === undefined ? (
    <Loading />
  ) : (
    <>
      <ResultTemplate
        judgeResult={judgeResult}
        hostFlag={hostFlag}
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
