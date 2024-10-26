import {FetchJoinedRoomStatus} from "@/features/top/type.ts";

type Props = {
    className: string;
    joinedRoomStatus: FetchJoinedRoomStatus;
    loading: boolean;
};

export const BackToRoomButton: React.FC<Props> = ({className, joinedRoomStatus, loading}) => {
    if (loading) {
        <div>loading...</div>
    }

    if (joinedRoomStatus === 'NOT_JOINED_ROOM') {
        return <></>
    } else {
        return (
            <button className={className} onClick={() => {
                location.href = '/room';
            }}>
                直前の部屋に再入室する
            </button>
        )
    }
}
