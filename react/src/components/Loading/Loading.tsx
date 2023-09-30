import { css } from 'styled-system/css';

const styles = {
  loading: css({
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    width: '100vw',
    height: '100vh',
  }),
};

export const Loading: React.FC = () => {
  return <div className={styles.loading}>読み込み中</div>;
};
