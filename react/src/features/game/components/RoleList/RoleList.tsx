import { css } from 'styled-system/css';
import { ContentBox } from '@/components';
import { roles } from '@/features/role';
import type { GameRule } from '@/type';

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
    display: 'grid',
    gridTemplateColumns: '3.5em 1fr 1em 1em',
    gap: '0.5rem',

    '& img': {
      width: '1.75rem',
      height: '1.75rem',
    },
  }),
  roleListItemCount: css({
    textAlign: 'center',
  }),
};

type Props = {
  gameRuleList: GameRule[];
};

export const RoleList: React.FC<Props> = ({ gameRuleList }) => {
  const getIconUrl = (roleId: number) => {
    const role = roles.filter((role) => {
      return role.roleId === roleId;
    });

    return role[0].iconPath;
  };

  return (
    <ContentBox>
      <p className={styles.title}>役職一覧</p>
      <ul className={styles.roleList}>
        {gameRuleList.map((gameRule, index) => {
          return (
            <li key={index} className={styles.roleListItem}>
              <span>{gameRule.roleName}</span>
              <img src={getIconUrl(gameRule.roleId)} alt={gameRule.roleName} />
              <span>×</span>
              <span className={styles.roleListItemCount}>{gameRule.count}</span>
            </li>
          );
        })}
      </ul>
    </ContentBox>
  );
};
