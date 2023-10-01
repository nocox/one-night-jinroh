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
  roleList: css({
    display: 'grid',
    justifyItems: 'center',
    gap: '0.5rem',
  }),

  roleListItem: css({
    display: 'flex',
    alignItems: 'center',
    gap: '0.5rem',
    '& img': {
      width: '1.75rem',
      height: '1.75rem',
    },
  }),
};

type RoleMap = {
  murabito: number;
  jinroh: number;
  uranaishi: number;
  kaito: number;
  kyojin: number;
};

export const RoleList: React.FC = () => {
  // FIXME 本来はサーバーから取得する
  const roleList: RoleMap = {
    murabito: 1,
    jinroh: 1,
    uranaishi: 1,
    kaito: 1,
    kyojin: 1,
  };

  const getIconUrl = (roleName: string) => {
    const character = characters.filter((character) => {
      return character.EnglishName === roleName;
    });

    return character[0].iconUrl;
  };

  return (
    <ContentBox>
      <p className={styles.title}>役職一覧</p>
      <ul className={styles.roleList}>
        {Object.entries(roleList).map(([role, count]) => (
          <li className={styles.roleListItem} key={role}>
            <img src={getIconUrl(role)} alt={role} />
            <span>×</span>
            <span>{count}</span>
          </li>
        ))}
      </ul>
    </ContentBox>
  );
};
