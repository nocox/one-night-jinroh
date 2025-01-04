import type React from 'react';
import { useState } from 'react';
import {BackToGameButton} from "@/features/room/components/BackToGameButton";
import { RoomTemplate } from './RoomTemplate';
import { exitRoom } from "./api.ts";
import { GameStartModal } from './components/GameStartModal';
import { useRoomData } from './hooks';
import type {FetchJoinGameResponse, GameInfo} from './type';
import {fetchGetWrapper, fetchPostWrapper} from "@/api";
import { InvalidResponseBodyError } from '@/features/error';
import type {FetchGameIdResponse} from "@/features/game/type";
import { useWebSocket } from '@/hooks';
import { useModal } from '@/hooks/useModal';
import type { Subscribe } from '@/type';

export const RoomPage: React.FC = () => {
  const { open, onOpenModal } = useModal(false);

  const onStartGame = (gameId: number) => {
      void fetchPostWrapper<FetchJoinGameResponse>('/join-game' + '?gameId=' + gameId.toString()).then((res) => {
          if (res.status === 'JOIN_GAME_SUCCESS') {
              window.location.href = '/night';
          }
      })
  };

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

  const [gameInfo, setGameInfo] = useState<GameInfo | undefined>(undefined);

  const roomIndexResponseBody = useRoomData();

  const { uuid } = roomIndexResponseBody;

  const subscribeGameStart: Subscribe = {
    path: `/topic/${uuid}`,
    callback: (message) => {
      if (message === undefined) {
        throw new InvalidResponseBodyError(
          `Invalid response body: ${JSON.stringify(message)}`,
        );
      }

      const gameInfo = message.body
        ? (JSON.parse(message.body) as GameInfo)
        : undefined;

      setGameInfo(gameInfo);
      onOpenModal();
    },
  };

  const subscribeFinishRoom: Subscribe = {
    path: `/topic/receive-finish-room/${uuid}`,
    callback: async () => {
      window.alert('ルームが解散されました');
      await exitRoom();
      window.location.href = '/';
    },
  };

  useWebSocket([subscribeGameStart, subscribeFinishRoom]);

  return (
    <>
      <RoomTemplate roomIndexResponseBody={roomIndexResponseBody} />
      {gameId !== undefined && (
          <BackToGameButton/>
      )}
      {gameInfo !== undefined && (
        <GameStartModal
          open={open}
          onStartGame={onStartGame}
          gameInfo={gameInfo}
        />
      )}
    </>
  );
};
