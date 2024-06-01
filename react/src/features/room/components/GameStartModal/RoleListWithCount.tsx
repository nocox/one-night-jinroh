import { css } from 'styled-system/css';
import type { GameInfo } from '@/features/room/type';

const styles = {
  roleList: css({
    margin: '0.5rem 0',
  }),
  roleListItem: css({
    display: 'grid',
    justifyContent: 'center',
    gridTemplateColumns: '4em 1em 1em',
  }),
};

type RoleCounts = {
  [K in string]: number;
};

/**
 * ゲーム内に存在する役職ごとの数を数える
 */
const countRole = (roleList: GameInfo['roleList']) => {
  return roleList.reduce<RoleCounts>((acc, role) => {
    if (acc[role.roleName] === undefined) {
      acc[role.roleName] = 1;
    } else {
      acc[role.roleName] += 1;
    }

    return acc;
  }, {});
};

type Props = {
  roleList: GameInfo['roleList'];
};

export const RoleListWithCount: React.FC<Props> = ({ roleList }) => {
  return (
    <ul className={styles.roleList}>
      {Object.entries(countRole(roleList)).map(([roleName, count], index) => {
        return (
          <li key={index} className={styles.roleListItem}>
            <span>{roleName}</span>
            <span>:</span>
            <span>{count}</span>
          </li>
        );
      })}
    </ul>
  );
};
