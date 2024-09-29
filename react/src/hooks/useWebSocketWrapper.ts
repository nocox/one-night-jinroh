import type {Subscribe} from "@/type.ts";
import {useWebSocket} from "@/hooks/useWebSocket.ts";

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
            path: `/topic/${gameId}/room-finish`,
            callback: () => {
                // FIXME: この後退出処理を実装
                console.log("room finish")
            },
        }
    ]
    useWebSocket(defaultSubscribes.concat(subscribes));
}
