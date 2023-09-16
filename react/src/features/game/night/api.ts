import type { FetchNightIndex, NightIndexResponseBody } from './type';
import { isNightIndexResponseBody } from './type';
import { JINROH_API_BASE_URL } from '@/url';

export const fetchNightIndex: FetchNightIndex = async () => {
  const response = await fetch(JINROH_API_BASE_URL + '/night-index', {
    method: 'GET',
    credentials: 'include',
  });

  const data = (await response.json()) as NightIndexResponseBody;

  if (!isNightIndexResponseBody(data)) {
    throw new Error('APIのレスポンス形式が不正です');
  }

  return data;
};
