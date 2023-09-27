import type {
  FetchDoneNightAct,
  FetchNightIndex,
  FetchNightKaitoActionResult,
  FetchNightUranaishiAction,
  NightIndexResponseBody,
  NightKaitoResult,
  NightUranaiResult,
  PostNightKaitoAction,
  PostNightUranaishiAction,
} from './type';
import {
  isNightIndexResponseBody,
  isNightKaitoResult,
  isNightUranaiResult,
} from './type';
import { InvalidResponseBodyError, UnexpectedError } from '@/error';
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
  });

  const nightKaitoResult = (await response.json()) as NightKaitoResult;

  if (!isNightKaitoResult(nightKaitoResult)) {
    throw new InvalidResponseBodyError(
      `responseBody is not in the expected format. body: ${JSON.stringify(
        nightKaitoResult,
      )}`,
    );
  }

  return nightKaitoResult;
};

export const fetchNightKaitoActionResult:
  | FetchNightKaitoActionResult
  | undefined = async () => {
  const response = await fetch(JINROH_API_BASE_URL + '/night/kaito', {
    method: 'GET',
    credentials: 'include',
  });

  const nightKaitoResult = (await response.json()) as NightKaitoResult | null;

  if (nightKaitoResult === null) {
    return undefined;
  }

  if (!isNightKaitoResult(nightKaitoResult)) {
    throw new InvalidResponseBodyError(
      `responseBody is not in the expected format. body: ${JSON.stringify(
        nightKaitoResult,
      )}`,
    );
  }

  return nightKaitoResult;
};

export const postNightUranaishiAction: PostNightUranaishiAction = async (
  dto,
) => {
  const response = await fetch(JINROH_API_BASE_URL + '/night/uranai', {
    method: 'POST',
    credentials: 'include',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(dto),
  });

  if (!response.ok) {
    throw new UnexpectedError(
      `failed to postNightUranaishiAction. status: ${
        response.status
      }, body: ${await response.text()}`,
    );
  }

  const nightUranaiResult = (await response.json()) as NightUranaiResult;

  if (!isNightUranaiResult(nightUranaiResult)) {
    throw new InvalidResponseBodyError(
      `responseBody is not in the expected format. body: ${JSON.stringify(
        nightUranaiResult,
      )}`,
    );
  }

  return nightUranaiResult;
};

export const fetchNightUranaishiAction: FetchNightUranaishiAction =
  async () => {
    const response = await fetch(JINROH_API_BASE_URL + '/night/uranai', {
      method: 'GET',
      credentials: 'include',
    });

    const nightUranaiResult =
      (await response.json()) as NightUranaiResult | null;

    if (nightUranaiResult === null) {
      return undefined;
    }

    if (!isNightUranaiResult(nightUranaiResult)) {
      throw new InvalidResponseBodyError(
        `responseBody is not in the expected format. body: ${JSON.stringify(
          nightUranaiResult,
        )}`,
      );
    }

    return nightUranaiResult;
  };

export const fetchDoneNightAct: FetchDoneNightAct = async () => {
  const response = await fetch(JINROH_API_BASE_URL + '/done-night-act', {
    method: 'GET',
    credentials: 'include',
  });

  if (!response.ok) {
    throw new UnexpectedError(
      `failed to fetchDoneNightAct. status: ${
        response.status
      }, body: ${await response.text()}`,
    );
  }

  return true;
};
