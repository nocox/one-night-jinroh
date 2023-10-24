import { ResultTemplate } from './ResultTemplate';
import { JudgeModal } from './components/JudgeModal/JudgeModal';
import { useResultData } from './hooks/useResultData';
import { Loading } from '@/components';
import { fetchReturnRoom } from '@/features/game/result/api';
import { useWebSocket } from '@/hooks';
import { useModal } from '@/hooks/useModal';
import type { Subscribe } from '@/type';

export const ResultPage: React.FC = () => {
  const { gameId, hostFlag, judgeResult, participants, holidayRoles } =
    useResultData();

  const { open, onCloseModal } = useModal(true);

  const subscribeReturnRoom: Subscribe = {
    path: `/topic/return-room/${gameId ?? ''}`,
    callback: () => {
      onCloseModal();
      window.location.href = '/room';
    },
  };

  const handleReturnRoom = async () => {
    await fetchReturnRoom();
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
