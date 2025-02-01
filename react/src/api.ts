import type { UseQueryOptions, UseQueryResult } from '@tanstack/react-query';
import { useQuery } from '@tanstack/react-query';
import { JINROH_API_BASE_URL } from './url';
import { UnexpectedError } from '@/features/error';

type FetchOptions = {
  headers?: HeadersInit;
  credentials?: RequestCredentials;
};

export async function fetchGetWrapper<T>(
  path: string,
  params?: Record<string, string>,
  { headers = {}, credentials = 'include' }: FetchOptions = {},
): Promise<T> {
  const queryString = params
    ? '?' + new URLSearchParams(params).toString()
    : '';

  const res = await fetch(JINROH_API_BASE_URL + path + queryString, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      ...headers,
    },
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

type PostOptions = {
  headers?: HeadersInit;
  body?: unknown;
  credentials?: RequestCredentials;
};

export async function fetchPostWrapper<T>(
  path: string,
  { headers = {}, body, credentials = 'include' }: PostOptions = {},
): Promise<T> {
  const res = await fetch(JINROH_API_BASE_URL + path, {
    method: 'POST',
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

export function useQueryWrapper<T>(
  options: UseQueryOptions<T, Error>,
): UseQueryResult<T | undefined, Error> {
  const useQueryResult = useQuery({
    ...options,
  });

  if (useQueryResult.error) {
    throw useQueryResult.error;
  }

  return useQueryResult;
}
