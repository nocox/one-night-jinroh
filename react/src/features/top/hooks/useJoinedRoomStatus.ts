import { useQuery } from '@tanstack/react-query';
import { fetchGetWrapper } from '@/api';
import type {
  FetchJoinedRoomStatus,
  FetchJoinedRoomStatusResponse,
} from '@/features/top/type';

export const useJoinedRoomStatus = (): {
  joinedRoomStatus: FetchJoinedRoomStatus | undefined;
  refetch: () => void;
  loading: boolean;
} => {
  const { data, isLoading, refetch } = useQuery({
    queryKey: ['joined-room'],
    queryFn: async () =>
      await fetchGetWrapper<FetchJoinedRoomStatusResponse>('/joined-room'),
  });

  return { joinedRoomStatus: data?.status, refetch, loading: isLoading };
};
