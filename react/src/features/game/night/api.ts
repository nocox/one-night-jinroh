import type {
  FetchDoneNightAct,
  FetchNightIndex,
  FetchNightJinrohPlayers,
  FetchNightKaitoActionResult,
  FetchNightUranaishiAction,
  NightIndexResponseBody,
  NightJinrohPlayers,
  NightKaitoResult,
  NightUranaiResult,
  PostNightKaitoAction,
  PostNightUranaishiAction,
} from './type';

import { UnexpectedError } from '@/features/error';
import { JINROH_API_BASE_URL } from '@/url';

export const fetchNightIndex: FetchNightIndex = async () => {
  const response = await fetch(JINROH_API_BASE_URL + '/night-index', {
    method: 'GET',
    credentials: 'include',
  });

  const data = (await response.json()) as NightIndexResponseBody;

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

    return nightUranaiResult;
  };

export const fetchNightJinrohPlayers: FetchNightJinrohPlayers = async () => {
  const response = await fetch(JINROH_API_BASE_URL + '/night/jinroh/index', {
    method: 'GET',
    credentials: 'include',
  });

  if (!response.ok) {
    throw new UnexpectedError(
      `failed to fetchNightJinrohPlayers. status: ${
        response.status
      }, body: ${await response.text()}`,
    );
  }

  const nightJinrohPlayers = (await response.json()) as NightJinrohPlayers;

  return nightJinrohPlayers;
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
