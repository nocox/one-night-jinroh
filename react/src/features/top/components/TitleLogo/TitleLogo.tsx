import { css } from 'styled-system/css';
import logoSp from './logo-sp.webp';
import logo from './logo.webp';

const styles = {
  titleLogo: css({
    display: 'flex',
    justifyContent: 'center',
    margin: '1rem auto',
    maxWidth: '640px',
  }),
};

export const TitleLogo: React.FC = () => {
  return (
    <div className={styles.titleLogo}>
      <picture>
        <source srcSet={logoSp} media="(max-width: 639px)" />
        <img src={logo} alt="ゆるふわ人狼" />
      </picture>
    </div>
  );
};
