import { useEffect, useState } from 'react';
import { getJudgeResult } from '@/features/game/result/service/getJudgeResult';
import type {
  JudgeResult,
  ShowResultTermIndexBean,
} from '@/features/game/result/type';

type Judge = ShowResultTermIndexBean['judge'];

export const useJudgeResult = (
  judge: Judge | undefined,
): JudgeResult | undefined => {
  const [judgeResult, setJudgeResult] = useState<JudgeResult | undefined>(
    undefined,
  );

  useEffect(() => {
    setJudgeResult(getJudgeResult(judge));
  }, [judge]);

  return judgeResult;
};
