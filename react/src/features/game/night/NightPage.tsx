import type React from 'react';
import {useState} from "react";
import { NightTemplate } from './NightTemplate';
import { useNightData } from './hooks/useNightData';
import { Loading } from '@/components';
import { useGameIndex } from '@/features/game/hooks/useGameIndex';
import {useWebSocketGameWrapper} from "@/hooks";
import type { Subscribe } from '@/type';
import {fetchGetWrapper} from "@/api";
import {FetchGameIdResponse} from "@/features/game/type";

// MEMO: GameIdをゲームページに渡す実装をお試し中。とりあえず夜の行動ページだけ反映中。
export const NightPage: React.FC = () => {
    const [gameId, setGameId] = useState<number | undefined>(undefined);

    void fetchGetWrapper<FetchGameIdResponse>('/fetch-game-id').then((res) => {
        switch (res.resultCode) {
            case "IN_GAME":
                setGameId(res.gameId);
                break
            case "NOT_IN_GAME":
                setGameId(undefined)
                break
        }
    })

    return gameId === undefined ? (
        <div>
            <p>gameId取得できなかったよ</p>

            <a href="/">Topへ戻る</a>
        </div>
    ) : (
        <InGameNightPage gameId={gameId}/>
    )
};

type Props = {
  gameId: number
}

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
}
