import { useQuery } from '@tanstack/react-query';
import type { RoomIndexResponseBody } from '../type';
import { fetchGetWrapper } from '@/api';

export const useRoomData = (): {
  uuid: RoomIndexResponseBody['uuid'] | undefined;
  userList: RoomIndexResponseBody['userList'] | undefined;
  hostFlg: RoomIndexResponseBody['hostFlg'] | undefined;
  myselfUserId: RoomIndexResponseBody['myselfUserId'] | undefined;
  isLoading: boolean;
} => {
  const { data, isLoading } = useQuery({
    queryKey: ['joined-room'],
    queryFn: async () =>
      await fetchGetWrapper<Promise<RoomIndexResponseBody>>('/room-index'),
  });

  return {
    uuid: data?.uuid,
    userList: data?.userList,
    hostFlg: data?.hostFlg,
    myselfUserId: data?.myselfUserId,
    isLoading,
  };
};
