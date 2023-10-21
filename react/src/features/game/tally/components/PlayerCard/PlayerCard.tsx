import { css, cx } from 'styled-system/css';
import type { GameParticipantWithVoteBean } from '@/features/game/tally/type';
import type { CoBean } from '@/features/game/type';
import { roles, unknownRole } from '@/features/role';

const styles = {
  playerCard: css({
    display: 'grid',
    gridTemplateColumns: '80px 1fr',
    columnGap: '1rem',
  }),
  playerName: css({
    gridColumn: '2 / 3',
    gridRow: '2 / 3',
    alignSelf: 'flex-end',
  }),
  icon: css({
    position: 'relative',
    gridColumn: '1 / 2',
    gridRow: '1 / 3',
  }),
  coIcon: css({
    gridColumn: '2 / 3',
    gridRow: '1 / 2',
    maxWidth: '3rem',
    maxHeight: '3rem',
  }),
  selected: css({
    '& img': {
      filter: 'grayscale(100%)',
    },
    _after: {
      position: 'absolute',
      left: '0',
      top: '0',
      width: 'calc(100% * 1.414)',
      height: '2px',
      content: '""',
      backgroundColor: 'red',
      transform: 'rotateZ(45deg)',
      transformOrigin: 'left top',
    },
    _before: {
      zIndex: '1',
      position: 'absolute',
      left: '0',
      bottom: '0',
      width: 'calc(100% * 1.414)',
      height: '2px',
      content: '""',
      backgroundColor: 'red',
      transform: 'rotateZ(-45deg)',
      transformOrigin: 'left bottom',
    },
  }),
};

type Props = {
  player: GameParticipantWithVoteBean;
  cos: CoBean[];
  isSelected: boolean;
};

export const PlayerCard: React.FC<Props> = ({ player, cos, isSelected }) => {
  const co = cos.find((co) => {
    return co.id === player.id;
  });

  const role = roles.filter((role) => {
    return role.japaneseName === player.role.roleName;
  });

  const iconUrl = role.length === 0 ? unknownRole.iconPath : role[0].iconPath;

  const coIconUrl = roles.filter((role) => role.englishName === co?.role)[0]
    ?.coIconPath;

  return (
    <div className={styles.playerCard}>
      <div
        className={isSelected ? cx(styles.icon, styles.selected) : styles.icon}
      >
        <img src={iconUrl} alt={player.role.roleName} />
      </div>
      {coIconUrl && (
        <img className={styles.coIcon} src={coIconUrl} alt={co?.role} />
      )}
      <span className={styles.playerName}>{player.name}</span>
    </div>
  );
};
