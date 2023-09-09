import { RoomTemplate } from './RoomTemplate';

import { useRoomData, useWebSocket } from './hooks';

export const RoomPage: React.FC = () => {
  const roomIndexResponseBody = useRoomData();

  const { uuid, hostFlg } = roomIndexResponseBody;
  useWebSocket(uuid, hostFlg);

  return (
    <>
      <RoomTemplate roomIndexResponseBody={roomIndexResponseBody} />
    </>
  );
};
