import murabitoWinImagePath from '@/assets/images/result/result1.png';
import jinrohWinImagePath from '@/assets/images/result/result2.png';
import turibitoWinImagePath from '@/assets/images/result/result3.png';
import { ExhaustiveError } from '@/features/error';
import type { JudgeResult } from '@/features/game/result/type';

export const getJudgeResult = (
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
