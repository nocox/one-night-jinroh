import { JINROH_API_BASE_URL } from './url';
import { UnexpectedError } from '@/features/error';

type FetchOptions = {
  method?: 'GET' | 'POST' | 'PUT' | 'DELETE';
  headers?: HeadersInit;
  body?: unknown;
  credentials?: RequestCredentials;
};

export async function fetchWraper<T>(
  path: string,
  {
    method = 'GET',
    headers = {},
    body,
    credentials = 'include',
  }: FetchOptions = {},
): Promise<T> {
  const res = await fetch(JINROH_API_BASE_URL + path, {
    method,
    headers: {
      'Content-Type': 'application/json',
      ...headers,
    },
    body: body !== undefined ? JSON.stringify(body) : undefined,
    credentials,
  });

  if (!res.ok) {
    const errorTxt = await res.text();
    throw new UnexpectedError(
      `Failed to fetch: ${res.status}, Body: ${errorTxt}.`,
    );
  }

  return (await res.json()) as T;
}
