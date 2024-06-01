import { css } from 'styled-system/css';
import { RoleListWithCount } from './RoleListWithCount.tsx';
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

  return (
    <Modal open={open} onClose={onCloseModal}>
      <p className={styles.head}>ゲームを開始します</p>
      <p className={styles.text}>参加人数 : {playerCount}人</p>
      <p className={styles.head}>役職一覧</p>
      <RoleListWithCount roleList={roleList} />
      <div className={styles.okButton}>
        <Button onClick={onCloseModal}>OK</Button>
      </div>
    </Modal>
  );
};
