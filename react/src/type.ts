import type { Message } from 'webstomp-client';
// websocketの型定義
export type Subscribe = {
  path: string;
  callback: (message?: Message) => void;
};

// GameRuleの型定義
export type GameRule = {
  roleId: number;
  roleName: '村人' | '人狼' | '占い師' | '怪盗' | '狂人' | '吊り人';
  count: number;
};

export type FetchGameRuleList = (gameId: number) => Promise<GameRule[]>;
