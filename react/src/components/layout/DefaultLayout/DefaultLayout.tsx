import { css } from 'styled-system/css';

type Props = {
  children: React.ReactNode;
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
      {children}
    </main>
  );
};
