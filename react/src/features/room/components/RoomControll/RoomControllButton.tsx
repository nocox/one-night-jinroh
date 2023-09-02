import Modal from 'react-responsive-modal';
import { css } from 'styled-system/css';
import { Button } from '@/components';

const styles = {
  root: css({
    display: 'flex',
    justifyContent: 'center',
    margin: '1rem auto',
  }),
  buttonWrapper: css({
    display: 'flex',
    justifyContent: 'center',
    gap: '1rem',
    flexWrap: 'wrap',
  }),
  errorMessage: css({
    textAlign: 'center',
    color: 'red.500',
    width: '100%',
  }),

  modal: {
    title: css({
      fontSize: '1.25rem',
      textAlign: 'center',
    }),
    text: css({
      padding: '0.5rem',
      textAlign: 'center',
    }),
    buttonWrapper: css({
      display: 'flex',
      justifyContent: 'center',
      gap: '1rem',
      marginTop: '2rem',
    }),
  },
};

type Props = {
  hostFlg: boolean;
  handleGameStart: () => void;
  handleGameExit: () => void;
  errorMessage: string;
  open: boolean;
  onOpenModal: () => void;
  onCloseModal: () => void;
};

export const RoomControllButton: React.FC<Props> = ({
  hostFlg,
  handleGameStart,
  handleGameExit,
  errorMessage,
  open,
  onOpenModal,
  onCloseModal,
}) => {
  return (
    <section className={styles.root}>
      {hostFlg ? (
        <div className={styles.buttonWrapper}>
          <Button
            color={'redFill'}
            size={'large'}
            shape={'round'}
            onClick={handleGameStart}
          >
            はじめる！
          </Button>
          <Button
            color={'redBorder'}
            size={'large'}
            shape={'round'}
            onClick={onOpenModal}
          >
            かいさん...
          </Button>
          {errorMessage && (
            <p className={styles.errorMessage}>{errorMessage}</p>
          )}
        </div>
      ) : (
        <p>ホストがゲームを始めるまでお待ちください！</p>
      )}
      <Modal
        open={open}
        onClose={onCloseModal}
        center
        showCloseIcon={false}
        classNames={{ modal: 'custom-modal' }}
      >
        <h2 className={styles.modal.title}>ルームを解散します</h2>

        <p className={styles.modal.text}>本当によろしいですか？</p>
        <div className={styles.modal.buttonWrapper}>
          <Button color={'redBorder'} onClick={handleGameExit}>
            かいさん
          </Button>
          <Button color={'redFill'} onClick={onCloseModal}>
            やっぱりつづける！
          </Button>
        </div>
      </Modal>
    </section>
  );
};
