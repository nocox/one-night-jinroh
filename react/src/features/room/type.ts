export type RoomIndexResponseBody = {
  uuid: string;
  userList: User[];
  hostFlg: boolean;
  myselfUserId: number;
};

export type User = {
  userId: number;
  name: string;
  hostFlg: boolean;
};

export type GameStartStatus = 'SUCCESS' | 'NOT_ENOUGH_PARTICIPANTS';

export type GameStartStatusResponse = {
  status: GameStartStatus
}

export type GameInfo = {
  gameId: number;
  playerCount: number;
  roleList: Array<{
    roleId: number;
    roleName: string;
  }>;
};

export type FinishRoomStatus = 'FINISHED_ROOM' | 'ROOM_NOT_EXIST';

export type FinishRoomStatusResponse = {
  status: FinishRoomStatus
}

export type FinishRoom = () => Promise<FinishRoomStatus>;
export type ExitRoom = () => Promise<void>;

export type FetchJoinGameResponse = {
  status: 'JOIN_GAME_SUCCESS' | 'JOIN_GAME_FAILED'
}