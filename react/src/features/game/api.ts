import { InvalidResponseBodyError, UnexpectedError } from '@/features/error';
import type { FetchGameIndex, GameIndex } from '@/features/game/type';
import { isGameIndexSchema } from '@/features/game/type';
import { JINROH_API_BASE_URL } from '@/url';

export const fetchGameIndex: FetchGameIndex = async () => {
  const res = await fetch(`${JINROH_API_BASE_URL}/game-index`, {
    method: 'GET',
    credentials: 'include',
  });

  if (!res.ok) {
    throw new UnexpectedError(`
      fetchVoteIndex failed: ${res.status} ${res.statusText}
    `);
  }

  const gameIndex = (await res.json()) as GameIndex;

  if (!isGameIndexSchema(gameIndex)) {
    throw new InvalidResponseBodyError(`
      fetchVoteIndex failed: ${JSON.stringify(gameIndex)}
    `);
  }

  return gameIndex;
};
