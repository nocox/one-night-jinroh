import { useQuery } from '@tanstack/react-query';
import { fetchGetWrapper } from '@/api';
import murabitoWinImagePath from '@/assets/images/result/result1.png';
import jinrohWinImagePath from '@/assets/images/result/result2.png';
import turibitoWinImagePath from '@/assets/images/result/result3.png';
import { ExhaustiveError } from '@/features/error';
import type {
  GameParticipantWithResultBean,
  JudgeResult,
  ShowResultTermIndexBean,
} from '@/features/game/result/type';
import type { RoleEnglishName } from '@/features/role';

const getJudgeResult = (
  judge:
    | undefined
    | 'FAIL_PEACE_VILLAGE'
    | 'SIMPLE_JINROH_WIN'
    | 'SIMPLE_VILLAGE_WIN'
    | 'SUCCESS_HIDE_JINROH_WIN'
    | 'SUCCESS_PEACE_VILLAGE'
    | 'TURIBITO_WIN',
): JudgeResult | undefined => {
  switch (judge) {
    case undefined:
      return undefined;
    case 'FAIL_PEACE_VILLAGE':
      return {
        text: '平和村失敗',
        imagePath: jinrohWinImagePath,
      };
    case 'SIMPLE_JINROH_WIN':
      return {
        text: '人狼陣営の勝利',
        imagePath: jinrohWinImagePath,
      };
    case 'SIMPLE_VILLAGE_WIN':
      return {
        text: '村人陣営の勝利',
        imagePath: murabitoWinImagePath,
      };
    case 'SUCCESS_HIDE_JINROH_WIN':
      return {
        text: '人狼陣営の勝利（潜伏成功）',
        imagePath: jinrohWinImagePath,
      };
    case 'SUCCESS_PEACE_VILLAGE':
      return {
        text: '平和村成功',
        imagePath: murabitoWinImagePath,
      };
    case 'TURIBITO_WIN':
      return {
        text: '吊り人の勝利',
        imagePath: turibitoWinImagePath,
      };
    default:
      throw new ExhaustiveError(judge);
  }
};

export const useResultData = (): {
  hostFlag: boolean | undefined;
  gameId: number | undefined;
  judgeResult: JudgeResult | undefined;
  participants: GameParticipantWithResultBean[] | undefined;
  holidayRoles: RoleEnglishName[] | undefined;
} => {
  const { data } = useQuery({
    queryKey: ['result-index'],
    queryFn: async () =>
      await fetchGetWrapper<ShowResultTermIndexBean>('/result-index'),
  });

  return {
    gameId: data?.gameId,
    hostFlag: data?.hostFlg,
    judgeResult: getJudgeResult(data?.judge),
    participants: data?.participants,
    holidayRoles: data?.holidayRoles,
  };
};
