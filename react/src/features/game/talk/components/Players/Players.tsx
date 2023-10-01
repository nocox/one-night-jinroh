import { css } from 'styled-system/css';
import { PlayerCard } from '../PlayerCard';
import { ContentBox } from '@/components';
import type { Player } from '@/features/game/talk/type';

const styles = {
  players: css({
    display: 'grid',
    justifyContent: 'center',
    gridTemplateColumns: 'repeat(auto-fit, minmax(300px, 1fr))',
    gap: '1rem',
  }),
};

type Props = {
  players: Player[];
};

export const Players: React.FC<Props> = ({ players }) => {
  return (
    <ContentBox className={styles.players}>
      {players.map((player) => (
        <PlayerCard key={player.id} player={player} />
      ))}
    </ContentBox>
  );
};
