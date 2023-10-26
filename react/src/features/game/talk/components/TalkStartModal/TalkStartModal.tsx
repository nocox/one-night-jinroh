import { css } from 'styled-system/css';
import { Button, Modal } from '@/components';
import { useModal } from '@/hooks/useModal';
const styles = {
  title: css({
    margin: '1rem auto',
    fontSize: '1.25rem',
    fontWeight: 'bold',
    textAlign: 'center',
  }),
  buttonWrapper: css({
    display: 'flex',
    justifyContent: 'center',
    margin: '1rem auto',
  }),
};

export const TalkStartModal: React.FC = () => {
  const { open, onCloseModal } = useModal(true);

  return (
    <Modal open={open} onClose={onCloseModal}>
      <h3 className={styles.title}>話し合いを始めてください</h3>
      <div className={styles.buttonWrapper}>
        <Button onClick={onCloseModal}>OK</Button>
      </div>
    </Modal>
  );
};
