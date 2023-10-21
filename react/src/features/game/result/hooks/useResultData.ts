import { useEffect, useState } from 'react';
import murabitoWinImagePath from '@/assets/images/result/result1.png';
import jinrohWinImagePath from '@/assets/images/result/result2.png';
import turibitoWinImagePath from '@/assets/images/result/result3.png';
import { ExhaustiveError } from '@/features/error';
import { fetchShowResultTermIndex } from '@/features/game/result/api';
import type {
  GameParticipantWithResultBean,
  JudgeResult,
} from '@/features/game/result/type';
import type { RoleEnglishName } from '@/features/role';

const getJudgeResult = (
  judge:
    | 'FAIL_PEACE_VILLAGE'
    | 'SIMPLE_JINROH_WIN'
    | 'SIMPLE_VILLAGE_WIN'
    | 'SUCCESS_HIDE_JINROH_WIN'
    | 'SUCCESS_PEACE_VILLAGE'
    | 'TURIBITO_WIN',
): JudgeResult => {
  switch (judge) {
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
  const [hostFlag, setHostFlag] = useState<boolean | undefined>();
  const [gameId, setGameId] = useState<number | undefined>();
  const [judgeResult, setJudgeResult] = useState<
    { text: string; imagePath: string } | undefined
  >();
  const [participants, setParticipants] = useState<
    GameParticipantWithResultBean[] | undefined
  >([]);
  const [holidayRoles, setHolidayRoles] = useState<
    RoleEnglishName[] | undefined
  >([]);

  useEffect(() => {
    const fetchShowResultTermIndexAsync = async () => {
      const result = await fetchShowResultTermIndex();
      console.log({ result });

      setGameId(result.gameId);
      setHostFlag(result.hostFlg);
      setJudgeResult(getJudgeResult(result.judge));
      setParticipants(result.participants);
      setHolidayRoles(result.holidayRoles);
    };

    void fetchShowResultTermIndexAsync();
  }, []);

  return { gameId, hostFlag, judgeResult, participants, holidayRoles };
};
