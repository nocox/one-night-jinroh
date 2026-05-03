import type React from 'react';
import { RoomControll } from './components/RoomControll';
import { RoomNumber } from './components/RoomNumber';
import { UserBoard } from './components/UserBoard';
import type { RoomIndexResponseBody } from './type';
import { DefaultLayout } from '@/components';

type Props = {
  uuid: RoomIndexResponseBody['uuid'];
  userList: RoomIndexResponseBody['userList'];
  hostFlg: RoomIndexResponseBody['hostFlg'];
  myselfUserId: RoomIndexResponseBody['myselfUserId'];
};

export const RoomTemplate: React.FC<Props> = ({
  uuid,
  userList,
  hostFlg,
  myselfUserId,
}) => {
  return (
    <DefaultLayout>
      <RoomNumber uuid={uuid} />

      <UserBoard userList={userList} myselfUserId={myselfUserId} />

      <RoomControll hostFlg={hostFlg} />
    </DefaultLayout>
  );
};
