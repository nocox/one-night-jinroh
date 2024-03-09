import { useState, type FormEventHandler } from 'react';
import { joinRoom } from '../../api';
import { JoinRoomButton } from './JoinRoomButton';
import { JoinRoomModal } from './JoinRoomModal';
import { ExhaustiveError } from '@/features/error';
import { useModal } from '@/hooks/useModal';

type Props = {
  className: string;
};

export const JoinRoom: React.FC<Props> = ({ className }) => {
  const [roomId, setRoomId] = useState('');
  const [joinRoomResult, setJoinRoomResult] = useState('');
  const { open, onOpenModal, onCloseModal } = useModal(false);

  const handleSubmit: FormEventHandler<HTMLFormElement> = async (event) => {
    event.preventDefault();

    try {
      const dto = { roomId };
      const status = await joinRoom(dto);

      switch (status) {
        case 'JOIN_SUCCESS':
          location.href = '/room';
          setJoinRoomResult('');
          break;
        case 'ROOM_NOT_EXIST':
          setJoinRoomResult('ルームが見つかりません');
          break;
        case 'PARTICPANT_LIMIT':
          setJoinRoomResult('ルームの参加者上限に達しています');
          break;
        default:
          throw new ExhaustiveError(status);
      }
    } catch (error) {
      console.log(error); // TODO: ErrorFallbackを実装する
    }
  };

  return (
    <>
      <JoinRoomButton className={className} onOpenModal={onOpenModal} />
      <JoinRoomModal
        open={open}
        onCloseModal={onCloseModal}
        roomId={roomId}
        setRoomId={setRoomId}
        joinRoomResult={joinRoomResult}
        handleSubmit={handleSubmit}
      />
    </>
  );
};
