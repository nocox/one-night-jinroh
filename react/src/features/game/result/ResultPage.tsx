import { ResultTemplate } from './ResultTemplate';
import { JudgeModal } from './components/JudgeModal/JudgeModal';
import { useResultData } from './hooks/useResultData';
import { Loading } from '@/components';
import { useWebSocket } from '@/hooks';
import { useModal } from '@/hooks/useModal';
// import type { Subscribe } from '@/type';

export const ResultPage: React.FC = () => {
  const { gameId, hostFlag, judgeResult, participants, holidayRoles } =
    useResultData();

  const { open, onCloseModal } = useModal(true);

  useWebSocket(gameId === undefined ? [] : []);

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
      />

      <JudgeModal
        open={open}
        onCloseModal={onCloseModal}
        judgeResult={judgeResult}
      />
    </>
  );
};
