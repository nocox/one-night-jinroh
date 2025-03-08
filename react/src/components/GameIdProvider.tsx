import type { FC, ReactNode } from 'react';
import { Loading } from './Loading';
import { useGameIdContext } from '@/hooks/useGameIdContext';

type Props = {
    children: (gameId: number) => ReactNode;
};

export const GameIdProvider: FC<Props> = ({ children }) => {
    const { gameId, isLoading, error, isInGame } = useGameIdContext();

    if (isLoading) {
        return <Loading />;
    }

    if (error) {
        return (
            <div>
                <p>エラーが発生しました: {error.message}</p>
                <a href="/">Topへ戻る</a>
            </div>
        );
    }

    if (!isInGame) {
        return (
            <div>
                <p>ゲームに参加していません</p>
                <a href="/">Topへ戻る</a>
            </div>
        );
    }

    return <>{children(gameId!)}</>;
};