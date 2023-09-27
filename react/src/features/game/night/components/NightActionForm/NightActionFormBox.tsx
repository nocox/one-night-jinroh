import { css } from 'styled-system/css';
import { Button } from '@/components';

const styles = {
  form: css({
    minHeight: '20rem',
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
  doneNightActText: css({
    margin: '1rem auto 0',
    textAlign: 'center',
  }),
};

type Props = {
  children: React.ReactNode;
  doneNightAct: boolean;
  handleDoneNightAct: (e: React.MouseEvent<HTMLButtonElement>) => void;
};

export const NightActionFormBox: React.FC<Props> = ({
  children,
  doneNightAct,
  handleDoneNightAct,
}) => {
  return (
    <>
      <form className={styles.form}>{children}</form>
      <Button
        disabled={doneNightAct}
        isDisabled={doneNightAct}
        customStyles={styles.completeButton}
        onClick={handleDoneNightAct}
      >
        完了
      </Button>

      {doneNightAct && (
        <p className={styles.doneNightActText}>
          夜の行動を完了しました。ほかのみんなが完了するまで待ってね！
        </p>
      )}
    </>
  );
};
