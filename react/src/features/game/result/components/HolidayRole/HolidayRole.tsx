import { css } from 'styled-system/css';
import type { RoleEnglishName } from '@/features/role';
import { getRoleByEnglishName } from '@/features/role';

const styles = {
  figure: css({
    maxWidth: '8rem',
  }),
};

type Props = {
  holidayRole: RoleEnglishName;
};

export const HolidayRole: React.FC<Props> = ({ holidayRole }) => {
  const role = getRoleByEnglishName(holidayRole);

  return (
    <figure className={styles.figure}>
      <img src={role.imgPath} alt={role.japaneseName} />
    </figure>
  );
};
