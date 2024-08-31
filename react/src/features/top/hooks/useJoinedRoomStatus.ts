import {useEffect, useState} from "react";
import {fetchJoinedRoom} from "@/features/top/api.ts";
import {FetchJoinedRoomStatus} from "@/features/top/type.ts";


export const useJoinedRoomStatus = (): {
    joinedRoomStatus: FetchJoinedRoomStatus;
} => {
    const [joinedRoomStatus, setJoinedRoomStatus] = useState<FetchJoinedRoomStatus>("NOT_JOINED_ROOM")

    useEffect(() => {
        const fetchJoinedRoomAsync = async () => {
            const res = await fetchJoinedRoom();
            setJoinedRoomStatus(res)
        };
        void fetchJoinedRoomAsync();
    }, []);

    return {joinedRoomStatus}
}