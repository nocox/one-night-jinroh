import { z } from 'zod';
import { coRoleSchema, gameIndexSchema } from '@/features/game/type';
import type { CoRole, GameIndex } from '@/features/game/type';
import type { Role } from '@/features/role';

/**
 * 話し合いページ読み込み時のレスポンス
 */

export type TalkIndexResponseBody = {
  gameId: number;
  gameIndex: GameIndex;
  cos: CoRole[];
};

const talkIndexResponseBodySchema = z.object({
  gameId: z.number(),
  gameIndex: gameIndexSchema,
  cos: z.array(coRoleSchema),
});

export const isTalkIndexResponseBody = (
  value: unknown,
): value is TalkIndexResponseBody => {
  return talkIndexResponseBodySchema.safeParse(value).success;
};

export type FetchTalkIndex = () => Promise<TalkIndexResponseBody>;

/**
 * COリクエスト用
 */
type PostCoDto = {
  playerId: number;
  role: Role['englishName'];
};

export type PostCo = (dto: PostCoDto) => Promise<void>;
