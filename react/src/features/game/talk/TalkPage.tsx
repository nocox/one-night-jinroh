import { TalkTemplate } from './TalkTemplate';
import { useTalkData } from './hooks/useTalkData';
import { Loading } from '@/components';

export const TalkPage: React.FC = () => {
  const { gameId, players } = useTalkData();

  return gameId === undefined || players === undefined ? (
    <Loading />
  ) : (
    <TalkTemplate gameId={gameId} players={players} />
  );
};
