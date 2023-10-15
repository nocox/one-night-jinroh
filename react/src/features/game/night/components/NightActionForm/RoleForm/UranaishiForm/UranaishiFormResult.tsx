import { css } from 'styled-system/css';
import { characters } from '@/features/game/character';
import type { RoleBean } from '@/features/game/type';

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
  roles: RoleBean[];
};

export const UranaishiFormResult: React.FC<Props> = ({ actLog, roles }) => {
  return (
    <>
      <p className={styles.actLog}>
        <b>占い結果</b>
        <br />
        {actLog}
      </p>
      <ul className={styles.roles}>
        {roles.map((role) => (
          <li key={role.roleId}>
            <figure>
              <img
                src={
                  characters.filter(
                    (character) => character.roleId === role.roleId,
                  )[0].imgUrl
                }
                alt={role.roleName}
              />
              <figcaption>{role.roleName}</figcaption>
            </figure>
          </li>
        ))}
      </ul>
    </>
  );
};
