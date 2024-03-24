import { InvalidResponseBodyError, UnexpectedError } from '@/features/error';
import { isGameIndexSchema} from '@/features/game/type';
import type { FetchGameIndex ,GameIndexResponse} from '@/features/game/type';
import { JINROH_API_BASE_URL } from '@/url';

export const fetchGameIndex: FetchGameIndex = async (param) => {
  const res = await fetch(`${JINROH_API_BASE_URL}/game-index?term=${param}`, {
    method: 'GET',
    credentials: 'include',
  });

  if (!res.ok) {
    throw new UnexpectedError(`
      fetchGameIndex failed: ${res.status} ${res.statusText}
    `);
  }

  const response = (await res.json()) as GameIndexResponse;

  if (!isGameIndexSchema(response)) {
    throw new InvalidResponseBodyError(`
      fetchGameIndex failed: ${JSON.stringify(response)}
    `);
  }

  return response;
};
