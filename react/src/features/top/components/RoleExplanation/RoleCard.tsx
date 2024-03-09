import { css } from 'styled-system/css';
import type { Role } from '@/features/role';

const styles = {
  roleCard: css({
    display: 'grid',
    gridTemplateColumns: '1fr',
    alignItems: 'center',
    rowGap: '0.5rem',
    maxWidth: '640px',
    margin: '2rem auto 0',
    md: {
      gridTemplateColumns: '10rem 1fr',
    },
  }),

  roleCardFigure: css({
    display: 'grid',
    justifyItems: 'center',
    maxWidth: '8rem',
    margin: '0 auto',
  }),
};

type Props = {
  role: Role;
};
export const RoleCard: React.FC<Props> = ({ role }) => {
  return (
    <div className={styles.roleCard}>
      <figure className={styles.roleCardFigure}>
        <img src={role.imgPath} alt={role.japaneseName} />
        <figcaption>{role.japaneseName}</figcaption>
      </figure>
      <p>{role.description}</p>
    </div>
  );
};
