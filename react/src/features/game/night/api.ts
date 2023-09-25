import type { FetchNightIndex, FetchNightKaitoActionResult, NightIndexResponseBody, NightKaitoResult, PostNightKaitoAction } from './type';
import { isNightIndexResponseBody, isNightKaitoResult } from './type';
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

export const postNightKaitoAction: PostNightKaitoAction = async (dto) => {
  const { participantId } = dto;

  const response = await fetch(JINROH_API_BASE_URL + '/night/kaito', {
    method: 'POST',
    credentials: 'include',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ participantId }),
  })

  const data = (await response.json()) as NightKaitoResult;

  if (!isNightKaitoResult(data)) {
    throw new Error('APIのレスポンス形式が不正です');
  }

  return data;
}

export const fetchNightKaitoActionResult: FetchNightKaitoActionResult | undefined = async () => {

  const response = await fetch(JINROH_API_BASE_URL + '/night/kaito', {
    method: 'GET',
    credentials: 'include',

  });


  const data = (await response.json()) as NightKaitoResult | null;

  if (data === null) {
    return undefined;
  }

  if (!isNightKaitoResult(data)) {
    throw new Error('APIのレスポンス形式が不正です');
  }

  return data;
}