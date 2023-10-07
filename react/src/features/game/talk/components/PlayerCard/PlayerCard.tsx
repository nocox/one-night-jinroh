import { css } from 'styled-system/css';
import { characters, unknownCharacter } from '@/features/game/character';
import type { Player } from '@/features/game/talk/type';

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
    gridColumn: '1 / 2',
    gridRow: '1 / 3',
  }),
  coIcon: css({
    gridColumn: '2 / 3',
    gridRow: '1 / 2',
    maxWidth: '3rem',
    maxHeight: '3rem',
  }),
};

type Props = {
  player: Player;
};

export const PlayerCard: React.FC<Props> = ({ player }) => {
  const character = characters.filter((character) => {
    return character.JapaneseName === player.role.roleName;
  });

  const iconUrl =
    character.length === 0 ? unknownCharacter.iconUrl : character[0].iconUrl;

  const coIconUrl = characters.filter(
    (character) => character.EnglishName === player.co?.role,
  )[0]?.coIconUrl;

  return (
    <div className={styles.playerCard}>
      <img className={styles.icon} src={iconUrl} alt={player.role.roleName} />
      {coIconUrl && (
        <img className={styles.coIcon} src={coIconUrl} alt={player.co?.role} />
      )}
      <span className={styles.playerName}>{player.name}</span>
    </div>
  );
};
