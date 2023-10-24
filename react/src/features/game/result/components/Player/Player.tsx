import { css, cx } from 'styled-system/css';
import type { GameParticipantWithResultBean } from '@/features/game/result/type';
import { getRoleByEnglishName } from '@/features/role';

const styles = {
  figure: css({
    display: 'grid',
    gridTemplateColumns: '5rem 5em',
    gap: '0.5rem 1rem',
    justifyContent: 'center',
  }),
  icon: css({
    gridColumn: '1 / 2',
    gridRow: '1 / 3',
    maxWidth: '5rem',
  }),
  coIcon: css({
    gridColumn: '2 / 3',
    maxWidth: '2rem',
  }),
  comment: css({
    gridColumn: '2 / 3',
    gridRow: '2 / 3',
  }),
  playerName: css({
    gridColumn: '1 / 3',
    gridRow: '3 / 4',
  }),
  myself: css({
    textDecoration: 'underline',
  }),
};

type Props = {
  participant: GameParticipantWithResultBean;
};

export const Player: React.FC<Props> = ({ participant }) => {
  const role = getRoleByEnglishName(participant.role);

  const coRole =
    participant.coRole !== '' ? getRoleByEnglishName(participant.coRole) : '';

  return (
    <figure className={styles.figure}>
      <img
        src={role.iconPath}
        alt={role.japaneseName}
        className={styles.icon}
      />
      {coRole !== '' && (
        <img
          src={coRole.coIconPath}
          alt={coRole.japaneseName}
          className={styles.coIcon}
        />
      )}
      <figcaption className={styles.comment}>
        {participant.comment !== '' && participant.comment}
      </figcaption>
      <figcaption
        className={cx(styles.playerName, participant.myself && styles.myself)}
      >
        {participant.playerName}
      </figcaption>
    </figure>
  );
};
