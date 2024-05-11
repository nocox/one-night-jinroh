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

type Props = {
  roleList: GameInfo['roleList'];
};

export const RoleListWithCount: React.FC<Props> = ({ roleList }) => {
  const roleCount = roleList
    .sort((a, b) => {
      return a.roleId - b.roleId;
    })
    .reduce<Record<string, number>>((acc, role) => {
      acc[role.roleName] = (acc[role.roleName] || 0) + 1;

      return acc;
    }, {});

  return (
    <ul className={styles.roleList}>
      {Object.entries(roleCount).map((role, index) => {
        return (
          <li key={index} className={styles.roleListItem}>
            <span>{role[0]}</span>
            <span>:</span>
            <span>{role[1]}</span>
          </li>
        );
      })}
    </ul>
  );
};
