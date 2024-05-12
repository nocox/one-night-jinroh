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

type Role = {
  roleId: number;
  roleName: string;
};

type RoleGroups = {
  [K in string]: Role[];
};

/**
 * roleListを役職ごとにグルーピングする
 * @param roleList
 * @return RoleGroups
 */
const groupBy = (roleList: GameInfo['roleList']) => {
  return roleList.reduce<RoleGroups>((acc, role) => {
    if (acc[role.roleName] === undefined) {
      acc[role.roleName] = [role];
    } else {
      acc[role.roleName].push(role);
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
      {Object.entries(groupBy(roleList)).map((role, index) => {
        return (
          <li key={index} className={styles.roleListItem}>
            <span>{role[0]}</span>
            <span>:</span>
            <span>{role[1].length}</span>
          </li>
        );
      })}
    </ul>
  );
};
