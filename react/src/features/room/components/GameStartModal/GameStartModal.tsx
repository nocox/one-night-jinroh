import { css } from 'styled-system/css';
import { Button, Modal } from '@/components';
import { characters } from '@/features/game/character';
import type { GameRuleList } from '@/type';

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
    display: 'grid',
    gap: '0.5rem',
    margin: '0.5rem 0',
  }),
  roleListItem: css({
    display: 'grid',
    justifyContent: 'center',
    alignItems: 'center',
    gridTemplateColumns: '3em 1.5em 1em 1em',
    gap: '0.5rem',
  }),
  okButton: css({
    display: 'block',
    margin: '1rem auto 0',
  }),
  textAlignCenter: css({
    textAlign: 'center',
  }),
};

type Props = {
  open: boolean;
  onCloseModal: () => void;
  gameRuleList: GameRuleList;
};

export const GameStartModal: React.FC<Props> = ({
  open,
  onCloseModal,
  gameRuleList,
}) => {
  const playerCount = gameRuleList.roleList.reduce((acc, role) => {
    return acc + role.count;
  }, -2);

  const roleList = gameRuleList.roleList;

  const getIconUrl = (roleId: number) => {
    const character = characters.filter((character) => {
      return character.roleId === roleId;
    });

    return character[0].iconUrl;
  };

  return (
    <Modal open={open} onClose={onCloseModal}>
      <p className={styles.head}>ゲームを開始します</p>
      <p className={styles.text}>参加人数 : {playerCount}人</p>
      <p className={styles.head}>役職一覧</p>
      <ul className={styles.roleList}>
        {roleList.map((role) => (
          <li key={role.roleId} className={styles.roleListItem}>
            <span>{role.roleName}</span>
            <span>
              <img src={getIconUrl(role.roleId)} alt={role.roleName} />
            </span>
            <span>:</span>
            <span className={styles.textAlignCenter}>{role.count}</span>
          </li>
        ))}
      </ul>
      <Button onClick={onCloseModal} customStyles={styles.okButton}>
        OK
      </Button>
    </Modal>
  );
};
