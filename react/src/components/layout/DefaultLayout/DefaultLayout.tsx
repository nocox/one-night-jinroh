import { ErrorBoundary } from 'react-error-boundary';
import { css } from 'styled-system/css';
import { ErrorFallback } from '@/components';

type Props = {
  children: React.ReactNode;
};

const onError = (error: Error, info: { componentStack: string }) => {
  // ここでログ出力などを行う
  console.log('error.message', error.message);
  console.log('info.componentStack:', info.componentStack);
};

export const DefaultLayout: React.FC<Props> = ({ children }) => {
  return (
    <main
      className={css({
        maxWidth: '1240px',
        margin: 'auto',
        padding: '0 1rem',
      })}
    >
      <ErrorBoundary FallbackComponent={ErrorFallback} onError={onError}>
        {children}
      </ErrorBoundary>
    </main>
  );
};
