import type React from 'react';
import { NightTemplate } from './NightTemplate';
import { useNightData } from './hooks/useNightData';
import { Loading } from '@/components';
import { useGameIndex } from '@/features/game/hooks/useGameIndex';
import { useWebSocketGameWrapper } from '@/hooks';
import { GameIdProvider } from '@/components/GameIdProvider';
import type { Subscribe } from '@/type';

export const NightPage: React.FC = () => {
    return (
        <GameIdProvider>
            {(gameId) => <InGameNightPage gameId={gameId} />}
        </GameIdProvider>
    );
};

type Props = {
    gameId: number;
};

const InGameNightPage: React.FC<Props> = ({gameId}) => {
    const { doneNightAct } = useNightData();
    const { playerName, playerRole, otherPlayerList, error } = useGameIndex(
        'night',
        gameId,
    );

    if (error) {
        throw error;
    }

    const subscribeDoneNightActionOfAllPlayer: Subscribe = {
        path: `/topic/${gameId}`,
        callback: () => {
            window.location.href = '/talk';
        },
    };

    useWebSocketGameWrapper(gameId, [subscribeDoneNightActionOfAllPlayer])

    return playerName === undefined ||
        playerRole === undefined ||
        otherPlayerList === undefined ||
        doneNightAct === undefined ? (
        <Loading />
    ) : (
        <>
            <NightTemplate
                playerName={playerName}
                playerRole={playerRole}
                otherPlayerList={otherPlayerList}
                doneNightAct={doneNightAct}
            />
        </>
    );
};
