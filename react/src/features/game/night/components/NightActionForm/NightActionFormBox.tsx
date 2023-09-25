import { css } from 'styled-system/css';
import { Button } from '@/components';

const styles = {
  form: css({
    height: '20rem',
    margin: '1rem auto 0',
    padding: '1rem',
    border: '3px solid #eee',
    borderRadius: '0.5rem',
  }),
  completeButton: css({
    display: 'block',
    width: '20rem',
    margin: '3rem auto 0',
  }),
};

type Props = {
  children: React.ReactNode;
  doneNightAct: boolean;
};

export const NightActionFormBox: React.FC<Props> = ({
  children,
  doneNightAct,
}) => {
  return (
    <>
      <form className={styles.form}>{children}</form>
      <Button disabled={doneNightAct} customStyles={styles.completeButton}>
        完了
      </Button>
    </>
  );
};
