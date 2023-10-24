import { InvalidResponseBodyError, UnexpectedError } from '@/features/error';
import { isShowResultTermIndexBean } from '@/features/game/result/type';
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

  const showResultTermIndexBean = (await res.json()) as ShowResultTermIndexBean;

  if (!isShowResultTermIndexBean(showResultTermIndexBean)) {
    throw new InvalidResponseBodyError(`
    responseBody is not in the expected format. body: ${JSON.stringify(
      showResultTermIndexBean,
    )}`);
  }

  return showResultTermIndexBean;
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
