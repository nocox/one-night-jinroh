import { useState } from 'react';
import { RoomNumberBox } from './RoomNumberBox';
import { useRoomData } from '@/features/room/hooks';

export const RoomNumber: React.FC = () => {
  const [isCopyDone, setIsCopyDone] = useState(false);

  const handleCopy = (uuid: string) => {
    void navigator.clipboard.writeText(uuid);
    setIsCopyDone(true);
  };

  const { roomIndexResponseBody } = useRoomData();

  return roomIndexResponseBody === undefined ? (
    <RoomNumberBox
      roomNumber=""
      isCopyDone={false}
      handleCopy={() => {
        handleCopy('');
      }}
    />
  ) : (
    <RoomNumberBox
      roomNumber={roomIndexResponseBody.uuid}
      isCopyDone={isCopyDone}
      handleCopy={() => {
        handleCopy(roomIndexResponseBody.uuid);
      }}
    />
  );
};
