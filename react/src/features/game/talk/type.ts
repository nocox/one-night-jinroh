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

export type FetchTalkIndex = () => Promise<TalkIndexResponseBody>;

/**
 * COリクエスト用
 */
type PostCoDto = {
  playerId: number;
  role: Role['englishName'];
};

export type PostCo = (dto: PostCoDto) => Promise<void>;
