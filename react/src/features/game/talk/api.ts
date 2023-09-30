import type { FetchTalkIndex, TalkIndexResponseBody } from './type';
import { isTalkIndexResponseBody } from './type';
import { InvalidResponseBodyError, UnexpectedError } from '@/error';
import { JINROH_API_BASE_URL } from '@/url';

export const fetchTalkIndex: FetchTalkIndex = async () => {
  const res = await fetch(JINROH_API_BASE_URL + '/talk-index', {
    method: 'GET',
    credentials: 'include',
  });

  if (!res.ok) {
    throw new UnexpectedError(
      `failed to postNightUranaishiAction. status: ${
        res.status
      }, body: ${await res.text()}`,
    );
  }

  const talkIndexResponseBody = (await res.json()) as TalkIndexResponseBody;

  if (!isTalkIndexResponseBody(talkIndexResponseBody)) {
    throw new InvalidResponseBodyError(
      `responseBody is not in the expected format. body: ${JSON.stringify(
        talkIndexResponseBody,
      )}`,
    );
  }

  return talkIndexResponseBody;
};
