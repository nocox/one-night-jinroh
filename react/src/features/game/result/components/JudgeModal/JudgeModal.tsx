import { css } from 'styled-system/css';
import { Button, Modal } from '@/components';
import type { JudgeResult } from '@/features/game/result/type';

const styles = {
  title: css({
    margin: '1rem auto',
    fontSize: '1.5rem',
    fontWeight: 'bold',
    textAlign: 'center',
  }),
  figure: css({
    margin: '1rem auto',
    maxWidth: '20rem',
  }),
  button: css({
    display: 'flex',
    justifyContent: 'center',
    margin: '2rem auto 1rem',
  }),
};

type Props = {
  open: boolean;
  onCloseModal: () => void;
  judgeResult: JudgeResult;
};

export const JudgeModal: React.FC<Props> = ({
  open,
  onCloseModal,
  judgeResult,
}) => {
  return (
    <Modal open={open} onClose={onCloseModal}>
      <h2 className={styles.title}>{judgeResult.text}</h2>
      <figure className={styles.figure}>
        <img src={judgeResult.imagePath} alt={judgeResult.text} />
      </figure>
      <div className={styles.button}>
        <Button onClick={onCloseModal}>OK</Button>
      </div>
    </Modal>
  );
};
