import { useState, useEffect } from 'react';
import { fetchGetWrapper } from '@/api';
import type { FetchGameIdResponse } from '@/features/game/type';

type GameIdState = {
    gameId: number | undefined;
    isLoading: boolean;
    error: Error | undefined;
};

export const useGameIdContext = () => {
    const [state, setState] = useState<GameIdState>({
        gameId: undefined,
        isLoading: true,
        error: undefined,
    });

    useEffect(() => {
        const fetchGameId = async () => {
            try {
                const response = await fetchGetWrapper<FetchGameIdResponse>('/fetch-game-id');
                setState({
                    gameId: response.resultCode === "IN_GAME" ? response.gameId : undefined,
                    isLoading: false,
                    error: undefined,
                });
            } catch (error) {
                setState({
                    gameId: undefined,
                    isLoading: false,
                    error: error instanceof Error ? error : new Error('Unknown error occurred'),
                });
            }
        };

        void fetchGameId();
    }, []);

    return {
        ...state,
        isInGame: state.gameId !== undefined && !state.isLoading && !state.error,
    };
};