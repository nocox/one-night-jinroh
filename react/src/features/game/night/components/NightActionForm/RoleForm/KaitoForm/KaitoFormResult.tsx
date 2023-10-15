import { css } from 'styled-system/css';
import type { RoleBean } from '@/features/game/type';
import { roles } from '@/features/role';

const styles = {
  actLog: css({
    margin: '1rem auto 0',
    textAlign: 'center',
    lineHeight: '2',
    '& b': {
      fontSize: '1.5rem',
    },
  }),
  roles: css({
    margin: '1rem auto 0',
    textAlign: 'center',
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
    gap: '0.25rem',
    '& img': {
      maxWidth: '10rem',
      maxHeight: '10rem',
    },
    md: {
      flexDirection: 'row',
    },
  }),
};

type Props = {
  actLog: string;
  roleBeans: RoleBean[];
};

export const KaitoFormResult: React.FC<Props> = ({ actLog, roleBeans }) => {
  return (
    <>
      <p className={styles.actLog}>{actLog}</p>

      <ul className={styles.roles}>
        {roleBeans.map((roleBean) => (
          <li key={roleBean.roleId}>
            <figure>
              <img
                src={
                  roles.filter((role) => role.roleId === roleBean.roleId)[0]
                    .imgPath
                }
                alt={roleBean.roleName}
              />
              <figcaption>{roleBean.roleName}</figcaption>
            </figure>
          </li>
        ))}
      </ul>
    </>
  );
};
