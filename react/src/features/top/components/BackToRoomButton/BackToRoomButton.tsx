import {FetchJoinedRoomStatus} from "@/features/top/type.ts";

type Props = {
    className: string;
    joinedRoomStatus: FetchJoinedRoomStatus;
};

export const BackToRoomButton: React.FC<Props> = ({className, joinedRoomStatus}) => {
    if (joinedRoomStatus === 'NOT_JOINED_ROOM') {
        return <></>
    } else {
        return (
            <button className={className} onClick={() => {
                location.href = '/room';
            }}>
                へやにもどる
            </button>
        )
    }
}
