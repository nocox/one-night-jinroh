import { TopTemplate } from './TopTemplate';
import {useJoinedRoomStatus} from "@/features/top/hooks/useJoinedRoomStatus.ts";

export const TopPage: React.FC = () => {
    const { joinedRoomStatus } = useJoinedRoomStatus();

  return (
    <>
      <TopTemplate joinedRoomStatus={joinedRoomStatus}/>
    </>
  );
};
