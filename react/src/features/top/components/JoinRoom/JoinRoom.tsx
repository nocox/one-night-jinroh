import type React from 'react';
import { useState, type FormEventHandler } from 'react';
import { JoinRoomButton } from './JoinRoomButton';
import { JoinRoomModal } from './JoinRoomModal';
import { fetchGetWrapper } from '@/api';
import { ExhaustiveError } from '@/features/error';
import { exitRoom, finishRoom } from '@/features/room/api';
import type { JoinedRoomStatus } from '@/features/top/type';
import { useModal } from '@/hooks/useModal';

type Props = {
  className: string;
  refetchJoinedRoomStatus: () => void;
};

export const JoinRoom: React.FC<Props> = ({
  className,
  refetchJoinedRoomStatus,
}) => {
  const [roomId, setRoomId] = useState('');
  const [joinRoomResult, setJoinRoomResult] = useState('');
  const { open, onOpenModal, onCloseModal } = useModal(false);

  const handleSubmit: FormEventHandler<HTMLFormElement> = async (event) => {
    event.preventDefault();

    try {
      const status = await fetchGetWrapper<JoinedRoomStatus>('/join-room', {
        uuid: roomId.toString(),
      });

      switch (status) {
        case 'JOIN_SUCCESS':
        case 'ALREADY_JOINED':
          location.href = '/room';
          setJoinRoomResult('');
          break;
        case 'OTHER_ROOM_JOINED':
          if (
            window.confirm('すでに参加済みのルームがあります。退出しますか？')
          ) {
            const finishStatus = await finishRoom();
            if (finishStatus === 'ROOM_NOT_EXIST') {
              await exitRoom();
            }
            refetchJoinedRoomStatus();
            setJoinRoomResult(
              '参加済みのルームを退出しました。参加したいルームIDを入力してください。',
            );
          } else {
            setJoinRoomResult('');
          }
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
