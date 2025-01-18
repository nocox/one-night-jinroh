import { useQuery } from '@tanstack/react-query';
import { fetchGetWrapper } from '@/api';
import type { NightIndexResponseBody } from '@/features/game/night/type';

export const useNightData = (): {
  gameId: number | undefined;
  doneNightAct: boolean | undefined;
} => {
  const { data, error } = useQuery({
    queryKey: ['night-index'],
    queryFn: async () => {
      return await fetchGetWrapper<NightIndexResponseBody>('/night-index');
    },
  });

  if (error) {
    throw error;
  }

  return {
    gameId: data?.gameId,
    doneNightAct: data?.doneNightAct,
  };
};
