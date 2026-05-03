import { useQuery, type UseQueryResult } from '@tanstack/react-query';
import { fetchGetWrapper } from '@/api';
import type { VoteIndexRequestBody } from '@/features/game/vote/type';

export const useVoteData = (): UseQueryResult<VoteIndexRequestBody> =>
  useQuery({
    queryKey: ['vote-index'],
    queryFn: async () => {
      return await fetchGetWrapper<VoteIndexRequestBody>('/vote-index');
    },
  });
