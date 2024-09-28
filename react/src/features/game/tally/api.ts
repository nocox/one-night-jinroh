import type {
  FetchResult,
  FetchTallyIndexResponseBody,
  TallyIndexResponseBody,
} from './type';
import { UnexpectedError } from '@/features/error';
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

    return (await res.json()) as TallyIndexResponseBody;
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
