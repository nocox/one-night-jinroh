import { createRoom } from '../../api';
import makeBtn from './make_room.png';
import {exitRoom, finishRoom} from "@/features/room/api.ts";

type Props = {
  className: string;
  refetchJoinedRoomStatus: ()=>void;
};

export const MakeRoomButton: React.FC<Props> = ({ className, refetchJoinedRoomStatus }) => {
  const handleClick = async () => {
    try {
      const status = await createRoom();

      switch (status) {
        case "CREATE_ROOM_SUCCESS":
          location.href = '/room';
          break;
        case "OTHER_ROOM_JOINED":
          if (window.confirm("すでに参加済みのルームがあります。退出しますか？")){
            const finishStatus = await finishRoom()
            if (finishStatus === "ROOM_NOT_EXIST") {
              await exitRoom()
            }
            refetchJoinedRoomStatus()
          }
          break;
      }

    } catch (error) {
      console.log(error); // TODO: ErrorFallbackを実装する
    }
  };

  return (
    <button className={className} onClick={handleClick}>
      <img src={makeBtn} alt="へやをつくる" />
    </button>
  );
};
