import { css, cx } from 'styled-system/css';
import { ContentBox } from '@/components';
import { postCo } from '@/features/game/talk/api';
import type { Player } from '@/features/game/talk/type';
import type { Role } from '@/features/role';
import { roles } from '@/features/role';

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
  iconsItemActive: css({
    '& img': {
      opacity: 1,
      border: '5px solid lightgreen',
    },
  }),
};

type Props = {
  getMyPlayer: () => Player;
};

export const ComingOut: React.FC<Props> = ({ getMyPlayer }) => {
  const coRoleEnglishName = getMyPlayer().co?.role;
  const coRole = roles.filter(
    (character) => character.englishName === coRoleEnglishName,
  )[0];

  const handleClick = async (character: Role) => {
    const role = character.englishName;
    const playerId = getMyPlayer().id;
    await postCo({ playerId, role });
  };

  return (
    <ContentBox>
      <p className={styles.title}>カミングアウト</p>
      <ul className={styles.icons}>
        {roles.map((character) => {
          return (
            <li
              key={character.roleId}
              className={cx(
                styles.iconsItem,
                coRole !== undefined &&
                  coRole.englishName === character.englishName &&
                  styles.iconsItemActive,
              )}
            >
              <button
                onClick={async () => {
                  await handleClick(character);
                }}
              >
                <img src={character.iconPath} alt={character.japaneseName} />
              </button>
            </li>
          );
        })}
      </ul>
    </ContentBox>
  );
};
