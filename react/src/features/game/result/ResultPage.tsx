import { ResultTemplate } from './ResultTemplate';
import { useResultData } from './hooks/useResultData';
import { Loading } from '@/components';
// import { useGameRule, useWebSocket } from '@/hooks';
// import type { Subscribe } from '@/type';

export const ResultPage: React.FC = () => {
  useResultData();
  // const { gameRuleList } = useGameRule();

  // subscribeのcallback関数を定義する
  // const subscribeHoge: Subscribe = {
  //   path: `${gameId ?? ''}`,
  //   callback: () => {
  //   },
  // };

  // useWebSocket(gameId === undefined ? [] : []);

  return (
    <>
      <Loading />
      <ResultTemplate />
    </>
  );
};
