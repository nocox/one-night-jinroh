import { css } from 'styled-system/css';
import { ContentBox } from '@/components';
import type { GameParticipantWithVoteBean } from '@/features/game/tally/type';

const styles = {
  title: css({
    margin: '1rem auto',
    fontSize: '1.25rem',
    fontWeight: 'bold',
    textAlign: 'center',
  }),
  votecountList: css({
    maxWidth: '12rem',
    margin: '0 auto',
  }),

  voteCountListItem: css({
    display: 'grid',
    gridTemplateColumns: '10em 1em 1fr',
  }),
};

type Props = {
  playersWithVoteCount: GameParticipantWithVoteBean[];
  children: React.ReactNode;
};
export const TallyBox: React.FC<Props> = ({
  playersWithVoteCount,
  children,
}) => {
  return (
    <ContentBox>
      <h3 className={styles.title}>得票数</h3>
      <ul className={styles.votecountList}>
        {playersWithVoteCount.map((player) => {
          return (
            <li key={player.id} className={styles.voteCountListItem}>
              {player.name}
              <span>:</span>
              {player.voteCount}
            </li>
          );
        })}
      </ul>
      {children}
    </ContentBox>
  );
};
