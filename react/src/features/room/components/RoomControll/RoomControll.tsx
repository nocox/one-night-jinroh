import { useState } from 'react';
import { fetchGameStart, finishRoom } from '../../api';
import { RoomControllButton } from './RoomControllButton';
import { ExhaustiveError } from '@/features/error';

type Props = {
  hostFlg: boolean;
};

export const RoomControll: React.FC<Props> = ({ hostFlg }) => {
  const [errorMessage, setErrorMessage] = useState<string>('');

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
      console.log(error); // TODO: ErrorFallback を実装する
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
      // TODO: 解散処理を実装する（ Web Socket で解散のメッセージを受信する）
    } catch (error) {
      console.log(error); // TODO: ErrorFallback を実装する
    }
  };

  return (
    <RoomControllButton
      hostFlg={hostFlg}
      errorMessage={errorMessage}
      handleGameStart={handleGameStart}
      handleGameExit={handleGameExit}
      open={open}
      onOpenModal={onOpenModal}
      onCloseModal={onCloseModal}
    />
  );
};
