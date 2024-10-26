import type React from 'react';
import { TopTemplate } from './TopTemplate';
import { useJoinedRoomStatus } from '@/features/top/hooks/useJoinedRoomStatus';

export const TopPage: React.FC = () => {
  const {
    joinedRoomStatus,
    refetch: refetchJoinedRoomStatus,
    loading: loadingJoinedRoomStatus,
  } = useJoinedRoomStatus();

  return (
    <>
      <TopTemplate
        joinedRoomStatus={joinedRoomStatus}
        refetchJoinedRoomStatus={refetchJoinedRoomStatus}
        loadingJoinedRoomStatus={loadingJoinedRoomStatus}
      />
    </>
  );
};
