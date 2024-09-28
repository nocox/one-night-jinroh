import { type FetchGameRuleList, type GameRule } from './type';
import { JINROH_API_BASE_URL } from './url';
import { UnexpectedError } from '@/features/error';

// GameRuleListを取得する関数
export const fetchGameRuleList: FetchGameRuleList = async (gameId) => {
  const res = await fetch(`${JINROH_API_BASE_URL}/game-rule/${gameId}`, {
    method: 'GET',
    credentials: 'include',
  });

  if (!res.ok) {
    throw new UnexpectedError(
      `failed to fetchGameRuleList. status: ${
        res.status
      }, body: ${await res.text()}`,
    );
  }

  return (await res.json()) as GameRule[];
};
