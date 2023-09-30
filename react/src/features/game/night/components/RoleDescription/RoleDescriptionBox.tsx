import { css } from 'styled-system/css';
import { ContentBox } from '@/components';
import type { Character } from '@/features/game/character';

const styles = {
  contentBox: css({
    display: 'grid',
    gridTemplateColumns: '1fr',
    alignItems: 'center',
    gap: '1rem',
    md: {
      gridTemplateColumns: '1fr 2fr',
    },
  }),
  roleImage: css({
    width: '100%',
    height: '100%',
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    gap: '0.5rem',
    '& img': {
      maxWidth: '10rem',
      maxHeight: '10rem',
      width: '100%',
      height: '100%',
    },
  }),
  description: css({
    height: '100%',
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    gap: '1rem',
    padding: '1rem',
    backgroundColor: '#fefefe',
    boxShadow: '5px 5px 5px rgba(0, 0, 0, 0.1)',
    borderRadius: '0.5rem',
  }),
};

type Props = {
  character: Character;
};

export const RoleDescriptionBox: React.FC<Props> = ({ character }) => {
  return (
    <ContentBox className={styles.contentBox}>
      <figure className={styles.roleImage}>
        <img src={character.imgUrl} alt={character.JapaneseName} />
        <figcaption>{character.JapaneseName}</figcaption>
      </figure>
      <div className={styles.description}>
        <p>あなたの役職は『{character.JapaneseName}』です。</p>
        <p>
          夜の行動
          <br />
          {character.nightAction}
        </p>
      </div>
    </ContentBox>
  );
};
