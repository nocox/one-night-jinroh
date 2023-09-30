import { TalkTemplate } from './TalkTemplate';
import { useTalkData } from './hooks/useTalkData';
import { Loading } from '@/components';

export const TalkPage: React.FC = () => {
  const talkIndex = useTalkData();

  return talkIndex === undefined ? (
    <Loading />
  ) : (
    <TalkTemplate talkIndex={talkIndex} />
  );
};
