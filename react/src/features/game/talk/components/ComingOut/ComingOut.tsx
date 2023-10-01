import { css } from 'styled-system/css';
import { ContentBox } from '@/components';
import { characters } from '@/features/game/character';

const styles = {
  title: css({
    margin: '1rem auto',
    fontSize: '1.25rem',
    fontWeight: 'bold',
    textAlign: 'center',
  }),
  icons: css({
    maxWidth: '30rem',
    margin: '0 auto',
    display: 'grid',
    gap: '1rem',
    gridTemplateColumns: 'repeat(2, minmax(80px, 1fr))',
    md: {
      gridTemplateColumns: 'repeat(3, minmax(80px, 1fr))',
    },
  }),
  iconsItem: css({
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    '& img': {
      width: '5rem',
      height: '5rem',
      borderRadius: '50%',
      opacity: 0.5,
      border: '5px solid transparent',
      _hover: {
        cursor: 'pointer',
      },
    },
  }),
};

export const ComingOut: React.FC = () => {
  return (
    <ContentBox>
      <p className={styles.title}>カミングアウト</p>
      <ul className={styles.icons}>
        {characters.map((character) => {
          return (
            <li key={character.roleId} className={styles.iconsItem}>
              <img src={character.iconUrl} alt={character.JapaneseName} />
            </li>
          );
        })}
      </ul>
    </ContentBox>
  );
};
