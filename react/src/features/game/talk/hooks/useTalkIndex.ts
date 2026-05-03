import type { UseQueryResult } from '@tanstack/react-query';
import { fetchGetWrapper, useQueryWrapper } from '@/api';
import { type TalkIndexResponseBody } from '@/features/game/talk/type';

export const useTalkIndex = (): UseQueryResult<TalkIndexResponseBody> =>
  useQueryWrapper({
    queryKey: ['talk-index'],
    queryFn: async () => {
      return await fetchGetWrapper<TalkIndexResponseBody>('/talk-index');
    },
  });
