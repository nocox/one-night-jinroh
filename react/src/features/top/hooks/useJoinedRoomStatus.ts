import {useEffect, useState} from "react";
import {fetchJoinedRoom} from "@/features/top/api.ts";
import {FetchJoinedRoomStatus} from "@/features/top/type.ts";


export const useJoinedRoomStatus = (): {
    joinedRoomStatus: FetchJoinedRoomStatus;
    refetch: () => void;
    loading: boolean;
} => {
    const [loading, setLoading] = useState(true)
    const [joinedRoomStatus, setJoinedRoomStatus] = useState<FetchJoinedRoomStatus>("NOT_JOINED_ROOM")
    const [refetchKey,setRefetchKey ] = useState(0)

    useEffect(() => {
        const fetchJoinedRoomAsync = async () => {
            try {
                const res = await fetchJoinedRoom();
                setJoinedRoomStatus(res)
            } catch(error) {
                // エラー処理
                console.log(error)
            } finally {
                setLoading(false)
            }
        };

        void fetchJoinedRoomAsync();
    }, [refetchKey]);

    const refetch = () => {
        setLoading(true)
        setRefetchKey(refetchKey + 1)
    }

    return {joinedRoomStatus, refetch, loading}
}