import { css, cx } from 'styled-system/css';
import { RoleCard } from './RoleCard';
import { ExhaustiveError } from '@/error';
import { roles } from '@/features/role';

const styles = {
  title: css({
    fontSize: '1.25rem',
    fontWeight: 'bold',
    padding: '0.25em',
    margin: '1.5rem auto 0',
  }),
};

const getThemeColor = (
  roleType: (typeof roles)[number]['roleType'],
): string => {
  switch (roleType) {
    case 'murabito':
      return '#b7ecb9';
    case 'jinroh':
      return '#ecd7b7';
    case 'turibito':
      return '#b7e2ec';
    default:
      throw new ExhaustiveError(roleType);
  }
};

const getTitle = (roleType: (typeof roles)[number]['roleType']): string => {
  switch (roleType) {
    case 'murabito':
      return '村人陣営';
    case 'jinroh':
      return '人狼陣営';
    case 'turibito':
      return '吊人陣営';
    default:
      throw new ExhaustiveError(roleType);
  }
};

const getRoleTypeDescription = (
  roleType: (typeof roles)[number]['roleType'],
): React.JSX.Element => {
  switch (roleType) {
    case 'murabito':
      return (
        <>
          人狼プレイヤーを一人でも吊ることができたら村人陣営の勝利です。
          <br />
          人狼プレイヤーが一人もいない平和村の場合は誰も吊らない(全員の得票数が1票以下になる)ことが勝利条件となります。
          <br />
          (吊人プレイヤーが勝利した場合は強制で敗北となります)
        </>
      );
    case 'jinroh':
      return (
        <>
          人狼プレイヤーが誰も吊られなければ人狼陣営の勝利です。
          <br />
          (吊人プレイヤーが勝利した場合は強制で敗北となります)
        </>
      );
    case 'turibito':
      return (
        <>
          吊人プレイヤーが吊られた場合、吊られたプレイヤーの勝利となり、他の陣営はすべて敗北となります。
        </>
      );
    default:
      throw new ExhaustiveError(roleType);
  }
};

type Props = {
  roleType: 'murabito' | 'jinroh' | 'turibito';
};

export const RoleExplanation: React.FC<Props> = ({ roleType }) => {
  return (
    <section>
      <h3
        className={cx(
          styles.title,
          css({ backgroundColor: getThemeColor(roleType) }),
        )}
      >
        {getTitle(roleType)}
      </h3>
      <p>{getRoleTypeDescription(roleType)}</p>
      <ul>
        {roles
          .filter((role) => {
            return role.roleType === roleType;
          })
          .map((role) => {
            return (
              <li key={role.roleId}>
                <RoleCard role={role} />
              </li>
            );
          })}
      </ul>
    </section>
  );
};
