import {fetchPostWrapper} from "@/api";
import {useWebSocket} from "@/hooks/useWebSocket";
import type {Subscribe} from "@/type";

// 全てのゲーム中の進行タームにおいて同じsubscribeを用意したい時に使う
export const useWebSocketGameWrapper = (gameId: number, subscribes: Subscribe[]): void => {
    const defaultSubscribes: Subscribe[] = [
        {
            path: `/topic/${gameId}/game-finish`,
            callback: () => {
                // FIXME: この後退出処理を実装
                console.log("game finish")
            },
        },
        {
            path: `/topic/${gameId}/leave-room`,
            callback: async () => {
                await fetchPostWrapper('/leave-room');
                window.location.href = '/';
            },
        }
    ]
    useWebSocket(defaultSubscribes.concat(subscribes));
}
