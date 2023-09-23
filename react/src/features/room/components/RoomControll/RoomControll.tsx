import { useState } from 'react';
import { useErrorBoundary } from 'react-error-boundary';
import { RoomControllButton } from './RoomControllButton';
import { Spinner } from '@/components';
import { ExhaustiveError } from '@/error';
import { fetchGameStart, finishRoom } from '@/features/room/api';
import { useRoomData } from '@/features/room/hooks';

export const RoomControll: React.FC = () => {
  const [errorMessage, setErrorMessage] = useState<string>('');
  const { showBoundary } = useErrorBoundary();

  const handleGameStart = async () => {
    try {
      const gameStartStatus = await fetchGameStart();
      switch (gameStartStatus) {
        case 'SUCCESS':
          console.log('ゲームスタート');
          break;
        case 'NOT_ENOUGH_PARTICIPANTS':
          setErrorMessage('参加人数が足りていません！');
          break;
        default:
          throw new ExhaustiveError(gameStartStatus);
      }
    } catch (error) {
      showBoundary(error);
    }
  };

  const [open, setOpen] = useState(false);

  const onOpenModal = () => {
    setOpen(true);
  };
  const onCloseModal = () => {
    setOpen(false);
  };

  const handleGameExit = async () => {
    try {
      await finishRoom();
    } catch (error) {
      showBoundary(error);
    }
  };

  const { roomIndexResponseBody } = useRoomData();

  return roomIndexResponseBody === undefined ? (
    <Spinner />
  ) : (
    <RoomControllButton
      hostFlg={roomIndexResponseBody.hostFlg}
      errorMessage={errorMessage}
      handleGameStart={handleGameStart}
      handleGameExit={handleGameExit}
      open={open}
      onOpenModal={onOpenModal}
      onCloseModal={onCloseModal}
    />
  );
};
