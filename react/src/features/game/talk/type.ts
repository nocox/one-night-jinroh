import type { CoRole, GameIndex } from '@/features/game/type';

/**
 * 話し合いページ読み込み時のレスポンス
 */

export type TalkIndexResponseBody = {
  gameId: number;
  gameIndex: GameIndex;
  cos: CoRole[];
};
