import { UnexpectedError } from '@/features/error';
import type {
  FetchReturnRoom,
  FetchShowResultTermIndex,
  ShowResultTermIndexBean,
} from '@/features/game/result/type';
import { JINROH_API_BASE_URL } from '@/url';

export const fetchShowResultTermIndex: FetchShowResultTermIndex = async () => {
  const res = await fetch(`${JINROH_API_BASE_URL}/result-index`, {
    method: 'GET',
    credentials: 'include',
  });

  if (!res.ok) {
    throw new UnexpectedError(`
    failed to postNightUranaishiAction. status: ${
      res.status
    }, body: ${await res.text()}`);
  }

  return (await res.json()) as ShowResultTermIndexBean;
};

export const fetchReturnRoom: FetchReturnRoom = async () => {
  const res = await fetch(`${JINROH_API_BASE_URL}/return-room`, {
    method: 'GET',
    credentials: 'include',
  });

  if (!res.ok) {
    throw new UnexpectedError(`
    failed to fetchReturnRoom. status: ${
      res.status
    }, body: ${await res.text()}`);
  }
};
