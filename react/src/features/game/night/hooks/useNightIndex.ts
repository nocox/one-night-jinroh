import type { UseQueryResult } from '@tanstack/react-query';
import { fetchGetWrapper, useQueryWrapper } from '@/api';
import type { NightIndexResponseBody } from '@/features/game/night/type';

export const useNightIndex = (): UseQueryResult<NightIndexResponseBody> =>
  useQueryWrapper({
    queryKey: ['night-index'],
    queryFn: async () => {
      return await fetchGetWrapper<NightIndexResponseBody>('/night-index');
    },
  });
