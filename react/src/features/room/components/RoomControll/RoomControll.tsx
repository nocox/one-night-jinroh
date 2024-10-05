import { useState } from 'react';
import { exitRoom, finishRoom } from '../../api';
import { RoomControllButton } from './RoomControllButton';
import { fetchGetWrapper } from '@/api';
import { ExhaustiveError } from '@/features/error';
import type { GameStartStatus } from '@/features/room/type';

type Props = {
  hostFlg: boolean;
};

export const RoomControll: React.FC<Props> = ({ hostFlg }) => {
  const [errorMessage, setErrorMessage] = useState<string>('');

  const handleGameStart = async () => {
    try {
      const gameStartStatus = await fetchGetWrapper<GameStartStatus>(
        '/game-start',
      );
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
      const finishStatus = await finishRoom();
      if (finishStatus === 'ROOM_NOT_EXIST') {
        await exitRoom();
      }
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
