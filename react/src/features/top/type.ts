export type JoinedRoomStatus =
  | 'JOIN_SUCCESS'
  | 'ROOM_NOT_EXIST'
  | 'PARTICPANT_LIMIT'
  | 'ALREADY_JOINED'
  | 'OTHER_ROOM_JOINED';

export type FetchJoinedRoomStatus = 'NOT_JOINED_ROOM' | 'JOINED_ROOM';
export type CreateRoomStatus = 'CREATE_ROOM_SUCCESS' | 'OTHER_ROOM_JOINED';
