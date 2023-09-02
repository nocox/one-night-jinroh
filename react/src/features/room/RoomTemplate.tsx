import { RoomControll } from './components/RoomControll';
import { RoomNumber } from './components/RoomNumber';
import { UserBoard } from './components/UserBoard';
import type { RoomIndexResponseBody } from './type';
import { DefaultLayout } from '@/components';

type Props = {
  roomIndexResponseBody: RoomIndexResponseBody;
};

export const RoomTemplate: React.FC<Props> = ({ roomIndexResponseBody }) => {
  const { uuid, userList, hostFlg, myselfUserId } = roomIndexResponseBody;

  return (
    <DefaultLayout>
      <RoomNumber uuid={uuid} />

      <UserBoard userList={userList} myselfUserId={myselfUserId} />

      <RoomControll hostFlg={hostFlg} />
    </DefaultLayout>
  );
};
