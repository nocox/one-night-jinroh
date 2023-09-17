import { useState, type FormEventHandler } from 'react';
import { useErrorBoundary } from 'react-error-boundary';
import { useNavigate } from 'react-router-dom';
import { joinRoom } from '../../api';
import { JoinRoomButton } from './JoinRoomButton';
import { ExhaustiveError } from '@/error';

type Props = {
  className: string;
};

export const JoinRoom: React.FC<Props> = ({ className }) => {
  const navigate = useNavigate();
  const { showBoundary } = useErrorBoundary();

  const [roomId, setRoomId] = useState('');
  const [joinRoomResult, setJoinRoomResult] = useState('');
  const [open, setOpen] = useState(false);

  const onOpenModal = () => {
    setOpen(true);
  };
  const onCloseModal = () => {
    setOpen(false);
  };

  const handleSubmit: FormEventHandler<HTMLFormElement> = async (event) => {
    event.preventDefault();

    try {
      const dto = { roomId };
      const status = await joinRoom(dto);

      switch (status) {
        case 'JOIN_SUCCESS':
          navigate('/room');
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
      showBoundary(error);
    }
  };

  return (
    <JoinRoomButton
      className={className}
      open={open}
      onOpenModal={onOpenModal}
      onCloseModal={onCloseModal}
      roomId={roomId}
      setRoomId={setRoomId}
      joinRoomResult={joinRoomResult}
      handleSubmit={handleSubmit}
    />
  );
};
