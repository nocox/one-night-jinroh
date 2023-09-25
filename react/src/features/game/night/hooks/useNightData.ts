import { useEffect, useState } from 'react';
import { fetchNightIndex } from '../api';
import type { NightIndexResponseBody } from '../type';

export const useNightData = (): NightIndexResponseBody | undefined => {

  const [nightIndexResponseBody, setNightIndexResponseBody] = useState<NightIndexResponseBody | undefined>(undefined);

  useEffect(() => {
    const fetchNightIndexAsync = async () => {
      setNightIndexResponseBody(await fetchNightIndex());
    };
    void fetchNightIndexAsync();
  }, []);

  return nightIndexResponseBody;
};
