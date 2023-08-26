import type { FormEventHandler } from 'react';
import { Modal } from 'react-responsive-modal';
import { css } from 'styled-system/css';
import joinBtn from './join_room.png';
import { Button } from '@/components';
import './custom-modal.css';
import 'react-responsive-modal/styles.css';

type Props = {
  className: string;
  open: boolean;
  onOpenModal: () => void;
  onCloseModal: () => void;
  roomId: string;
  setRoomId: React.Dispatch<React.SetStateAction<string>>;
  joinRoomResult: string;
  handleSubmit: FormEventHandler<HTMLFormElement>;
};

const styles = {
  modal: {
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
  },
};

export const JoinRoomButton: React.FC<Props> = ({
  className,
  open,
  onOpenModal,
  onCloseModal,
  roomId,
  setRoomId,
  joinRoomResult,
  handleSubmit,
}) => {
  return (
    <>
      <button className={className} onClick={onOpenModal}>
        <img src={joinBtn} alt="へやにはいる" />
      </button>
      <Modal
        open={open}
        onClose={onCloseModal}
        center
        showCloseIcon={false}
        classNames={{ modal: 'custom-modal' }}
      >
        <h2 className={styles.modal.title}>ルームに参加</h2>
        <form onSubmit={handleSubmit}>
          <input
            className={styles.modal.input}
            type="text"
            placeholder="ルーム番号"
            value={roomId}
            onChange={(event) => {
              setRoomId(event.target.value);
            }}
          />
          {joinRoomResult && (
            <p className={styles.modal.errorMessage}>{joinRoomResult}</p>
          )}
          <div className={styles.modal.buttonWrapper}>
            <Button styleType={'filled'} type="submit">
              参加
            </Button>
            <Button onClick={onCloseModal} type="button">
              戻る
            </Button>
          </div>
        </form>
      </Modal>
    </>
  );
};
