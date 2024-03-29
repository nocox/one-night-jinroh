import { isTallyIndexResponseBody } from './type';
import type {
  FetchResult,
  FetchTallyIndexResponseBody,
  TallyIndexResponseBody,
} from './type';
import { InvalidResponseBodyError, UnexpectedError } from '@/features/error';
import { JINROH_API_BASE_URL } from '@/url';

export const fetchTallyIndexResponseBody: FetchTallyIndexResponseBody =
  async () => {
    const res = await fetch(JINROH_API_BASE_URL + '/tally-index', {
      method: 'GET',
      credentials: 'include',
    });

    if (!res.ok) {
      throw new UnexpectedError(`
      fetchTallyIndexResponseBody failed with status ${res.status}
    `);
    }

    const tallyIndexResponseBody = (await res.json()) as TallyIndexResponseBody;

    if (!isTallyIndexResponseBody(tallyIndexResponseBody)) {
      throw new InvalidResponseBodyError(`
      fetchTallyIndexResponseBody failed with invalid response body
      ${JSON.stringify(tallyIndexResponseBody)}
    `);
    }

    return tallyIndexResponseBody;
  };

export const fetchResult: FetchResult = async () => {
  const res = await fetch(JINROH_API_BASE_URL + '/show-result', {
    method: 'GET',
    credentials: 'include',
  });

  if (!res.ok) {
    throw new UnexpectedError(`
    fetchResult failed with status ${res.status}
    `);
  }
};
