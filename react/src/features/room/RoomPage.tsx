import { RoomTemplate } from './RoomTemplate';

import { useRoomData } from './hooks';

export const RoomPage: React.FC = () => {
  const roomIndexResponseBody = useRoomData();

  return (
    <>
      <RoomTemplate roomIndexResponseBody={roomIndexResponseBody} />
    </>
  );
};
