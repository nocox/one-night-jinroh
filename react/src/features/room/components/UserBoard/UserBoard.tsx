import { UserBoardBox } from './UserBoardBox';
import { useRoomData } from '@/features/room/hooks';

export const UserBoard: React.FC = () => {
  const { roomIndexResponseBody } = useRoomData();

  return roomIndexResponseBody === undefined ? (
    <UserBoardBox myselfUserId={0} userList={[]} />
  ) : (
    <UserBoardBox
      myselfUserId={roomIndexResponseBody.myselfUserId}
      userList={roomIndexResponseBody.userList}
    />
  );
};
