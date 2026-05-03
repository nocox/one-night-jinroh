import type { UseQueryResult } from '@tanstack/react-query';
import { fetchGetWrapper, useQueryWrapper } from '@/api';
import type { ShowResultTermIndexBean } from '@/features/game/result/type';

export const useResultData = (): UseQueryResult<
  ShowResultTermIndexBean | undefined
> =>
  useQueryWrapper({
    queryKey: ['result-index'],
    queryFn: async () =>
      await fetchGetWrapper<ShowResultTermIndexBean>('/result-index'),
  });
