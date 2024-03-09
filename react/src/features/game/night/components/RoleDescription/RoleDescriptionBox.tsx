import { css } from 'styled-system/css';
import { ContentBox } from '@/components';
import type { Role } from '@/features/role';

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
  role: Role;
};

export const RoleDescriptionBox: React.FC<Props> = ({ role }) => {
  return (
    <ContentBox className={styles.contentBox}>
      <figure className={styles.roleImage}>
        <img src={role.imgPath} alt={role.japaneseName} />
        <figcaption>{role.japaneseName}</figcaption>
      </figure>
      <div className={styles.description}>
        <p>あなたの役職は『{role.japaneseName}』です。</p>
        <p>
          夜の行動
          <br />
          {role.nightAction}
        </p>
      </div>
    </ContentBox>
  );
};
