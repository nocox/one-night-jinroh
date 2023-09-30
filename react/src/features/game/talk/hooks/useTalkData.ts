import { useEffect, useState } from 'react';
import { fetchTalkIndex } from '../api';
import type { TalkIndexResponseBody } from '../type';

export const useTalkData = (): TalkIndexResponseBody | undefined => {
  const [talkIndexResponseBody, setTalkIndexResponseBody] = useState<
    TalkIndexResponseBody | undefined
  >(undefined);

  useEffect(() => {
    const fetchTalkIndexAsync = async () => {
      const talkIndexResponseBody = await fetchTalkIndex();
      setTalkIndexResponseBody(talkIndexResponseBody);
    };

    void fetchTalkIndexAsync();
  }, []);

  return talkIndexResponseBody;
};
