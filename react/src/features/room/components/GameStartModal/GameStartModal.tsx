import { css } from 'styled-system/css';
import { Button, Modal } from '@/components';
import type { GameInfo } from '@/features/room/type';

const styles = {
  head: css({
    fontSize: '1.25rem',
    textAlign: 'center',
  }),
  text: css({
    textAlign: 'center',
    margin: '0.5rem 0',
  }),
  roleList: css({
    margin: '0.5rem 0',
  }),
  roleListItem: css({
    display: 'grid',
    justifyContent: 'center',
    gridTemplateColumns: '4em 1em 1em',
  }),
  okButton: css({
    display: 'flex',
    justifyContent: 'center',
    margin: '1rem auto 0',
  }),
};

type Props = {
  open: boolean;
  onCloseModal: () => void;
  gameInfo: GameInfo;
};

export const GameStartModal: React.FC<Props> = ({
  open,
  onCloseModal,
  gameInfo,
}) => {
  const { roleList, playerCount } = gameInfo;

  const roleCountMap: Record<string, number> = {};
  roleList.forEach((record) => {
    const roleName = record.roleName;
    if (roleCountMap[roleName] === undefined) {
      roleCountMap[roleName] = 1;
    } else {
      roleCountMap[roleName] += 1;
    }
  });

  return (
    <Modal open={open} onClose={onCloseModal}>
      <p className={styles.head}>ゲームを開始します</p>
      <p className={styles.text}>参加人数 : {playerCount}人</p>
      <p className={styles.head}>役職一覧</p>
      <ul className={styles.roleList}>
        {roleList.map((role) => (
          <li key={role.roleId} className={styles.roleListItem}>
            <span>{role.roleName}</span>
            <span>:</span>
            <span>{roleCountMap[role.roleName]}</span>
          </li>
        ))}
      </ul>
      <div className={styles.okButton}>
        <Button onClick={onCloseModal}>OK</Button>
      </div>
    </Modal>
  );
};
