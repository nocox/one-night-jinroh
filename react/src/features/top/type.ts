type JoinRoomDto = {
  roomId: string;
};

export type JoinedRoomStatus =
  | 'JOIN_SUCCESS'
  | 'ROOM_NOT_EXIST'
  | 'PARTICPANT_LIMIT'
  | 'ALREADY_JOINED'
  | 'OTHER_ROOM_JOINED';

export type CreateRoomStatus = 'CREATE_ROOM_SUCCESS' | 'OTHER_ROOM_JOINED';

export type JoinRoom = (dto: JoinRoomDto) => Promise<JoinedRoomStatus>;
export type CreateRoom = () => Promise<CreateRoomStatus>;
