import type { Message } from 'webstomp-client';
import { z } from 'zod';
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

export type GameRuleList = {
  roleList: GameRule[];
};

export const gameRuleListSchema = z.object({
  roleList: z.array(
    z.object({
      roleId: z.number(),
      roleName: z.union([
        z.literal('村人'),
        z.literal('人狼'),
        z.literal('占い師'),
        z.literal('怪盗'),
        z.literal('狂人'),
        z.literal('吊り人'),
      ]),
      count: z.number(),
    }),
  ),
});

export const isGameRuleList = (value: unknown): value is GameRuleList => {
  return gameRuleListSchema.safeParse(value).success;
};

export type FetchGameRuleList = (gameId: number) => Promise<GameRuleList>;
