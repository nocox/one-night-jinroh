import { css } from 'styled-system/css';
import pcChara from './chara_sort_pc.webp';
import spChara from './chara_sort_sp.webp';

const styles = {
  titleCharacters: css({
    margin: '1rem auto',
    maxWidth: '1000px',
  }),
};

export const TitleCharacters: React.FC = () => {
  return (
    <div className={styles.titleCharacters}>
      <picture>
        <source srcSet={spChara} media="(max-width:639px)" />
        <img src={pcChara} alt="キャラクターイラスト" />
      </picture>
    </div>
  );
};
