import type { FallbackProps } from 'react-error-boundary';
import { css } from 'styled-system/css';
import { ErrorMessage } from './ErrorMessage';
import { Button } from '@/components';

const styles = {
  allert: css({
    backgroundColor: '#FFF5F5',
    margin: '2rem auto 0',
    padding: '1rem',
    borderRadius: '0.5rem',
  }),
  button: css({
    margin: '1rem auto',
  }),
};

type Props = {
  error: Error;
  resetErrorBoundary: FallbackProps['resetErrorBoundary'];
};

export const ErrorFallback: React.FC<Props> = ({
  error, // MEMO: もしエラーの種類によって表示するメッセージを変えたい場合はコメントアウトを外す
  resetErrorBoundary,
}) => {
  return (
    <>
      <div role="alert" className={styles.allert}>
        <ErrorMessage
          error={error} // MEMO: もしエラーの種類によって表示するメッセージを変えたい場合はコメントアウトを外す
        />
      </div>
      <Button
        customStyle={styles.button}
        onClick={resetErrorBoundary}
        color={'redFill'}
      >
        もう一度試す
      </Button>
    </>
  );
};
