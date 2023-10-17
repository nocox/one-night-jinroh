import { TallyTemplate } from './TallyTemplate';
import { useTallyData } from './hooks/useTallyData';
import { Loading } from '@/components';
import { useGameRule } from '@/hooks';

export const TallyPage: React.FC = () => {
  const { hostFlag, selectedPlayers, playersWithVoteCount, isPeaceful, cos } =
    useTallyData();
  const { gameRuleList } = useGameRule();

  return hostFlag === undefined ||
    selectedPlayers === undefined ||
    playersWithVoteCount === undefined ||
    isPeaceful === undefined ||
    cos === undefined ||
    gameRuleList === undefined ? (
    <Loading />
  ) : (
    <TallyTemplate
      hostFlag={hostFlag}
      selectedPlayers={selectedPlayers}
      playersWithVoteCount={playersWithVoteCount}
      gameRuleList={gameRuleList}
      isPeaceful={isPeaceful}
      cos={cos}
    />
  );
};
