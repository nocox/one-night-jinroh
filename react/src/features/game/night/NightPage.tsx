import { NightTemplate } from './NightTemplate';
import { useNightData } from './hooks/useNightData';

export const NightPage: React.FC = () => {
  const { doneNightAct, gameId, gameIndex } = useNightData();

  return (
    <>
      <NightTemplate
        doneNightAct={doneNightAct}
        gameId={gameId}
        gameIndex={gameIndex}
      />
    </>
  );
};
