import {fetchPostWrapper} from "@/api";
import {useWebSocket} from "@/hooks/useWebSocket";
import type {Subscribe} from "@/type";

// 全てのゲーム中の進行タームにおいて同じsubscribeを用意したい時に使う
export const useWebSocketGameWrapper = (gameId: number, subscribes: Subscribe[]): void => {
    const defaultSubscribes: Subscribe[] = [
        {
            path: `/topic/${gameId}/leave-room`,
            callback: async () => {
                await fetchPostWrapper('/leave-room');
                window.location.href = '/';
            },
        },
        {
            path: `/topic/${gameId}/leave-game`,
            callback: async () => {
                await fetchPostWrapper('/leave-game');
                window.location.href = '/room';
            },
        },
    ]
    useWebSocket(defaultSubscribes.concat(subscribes));
}
