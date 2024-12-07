import type React from 'react';
import { TopTemplate } from './TopTemplate';
import {Loading} from "@/components";
import { useJoinedRoomStatus } from '@/features/top/hooks/useJoinedRoomStatus';

export const TopPage: React.FC = () => {
  const {
    joinedRoomStatus,
    refetch: refetchJoinedRoomStatus,
    loading: loadingJoinedRoomStatus,
  } = useJoinedRoomStatus();

  if (joinedRoomStatus === undefined || loadingJoinedRoomStatus) {
    return <Loading />;
  }

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
