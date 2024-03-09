import { css } from 'styled-system/css';
import { PlayerCard } from '../PlayerCard';
import { ContentBox } from '@/components';
import type { GameParticipantWithVoteBean } from '@/features/game/tally/type';
import { type CoRole } from '@/features/game/type';

const styles = {
  players: css({
    display: 'grid',
    justifyContent: 'center',
    gridTemplateColumns: 'repeat(auto-fit, minmax(300px, 1fr))',
    gap: '1rem',
  }),
};

type Props = {
  players: GameParticipantWithVoteBean[];
  selectedPlayers: GameParticipantWithVoteBean[];
  isPeaceful: boolean;
  cos: CoRole[];
};

export const Players: React.FC<Props> = ({
  players,
  selectedPlayers,
  isPeaceful,
  cos,
}) => {
  const isSelected = (player: GameParticipantWithVoteBean) => {
    const isSelected = selectedPlayers.find((selectedPlayer) => {
      return selectedPlayer.id === player.id;
    });

    return isSelected !== undefined;
  };

  return (
    <ContentBox className={styles.players}>
      {players.map((player) => (
        <PlayerCard
          key={player.id}
          player={player}
          cos={cos}
          isSelected={isSelected(player) && !isPeaceful}
        />
      ))}
    </ContentBox>
  );
};
