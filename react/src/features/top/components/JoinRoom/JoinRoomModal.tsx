import type { FormEventHandler } from 'react';
import { css } from 'styled-system/css';
import { Button, Modal } from '@/components';

const styles = {
  title: css({
    fontSize: '1.25rem',
    textAlign: 'center',
  }),
  input: css({
    display: 'block',
    margin: '1rem auto 0',
    maxWidth: '90%',
    width: '100%',
    border: '1px solid #ccc',
    padding: '0.5rem',
  }),
  buttonWrapper: css({
    display: 'flex',
    justifyContent: 'center',
    gap: '1rem',
    marginTop: '1rem',
  }),
  errorMessage: css({
    color: 'red',
    textAlign: 'center',
  }),
};

type Props = {
  open: boolean;

  onCloseModal: () => void;
  roomId: string;
  setRoomId: React.Dispatch<React.SetStateAction<string>>;
  joinRoomResult: string;
  handleSubmit: FormEventHandler<HTMLFormElement>;
};

export const JoinRoomModal: React.FC<Props> = ({
  open,
  onCloseModal,
  roomId,
  setRoomId,
  joinRoomResult,
  handleSubmit,
}) => {
  return (
    <Modal open={open} onClose={onCloseModal}>
      <h2 className={styles.title}>ルームに参加</h2>
      <form onSubmit={handleSubmit}>
        <input
          className={styles.input}
          type="text"
          placeholder="ルーム番号"
          value={roomId}
          onChange={(event) => {
            setRoomId(event.target.value);
          }}
        />
        {joinRoomResult && (
          <p className={styles.errorMessage}>{joinRoomResult}</p>
        )}
        <div className={styles.buttonWrapper}>
          <Button colorType={'blue'} buttonType={'filled'} type="submit">
            参加
          </Button>
          <Button onClick={onCloseModal} type="button">
            戻る
          </Button>
        </div>
      </form>
    </Modal>
  );
};
