import { css } from 'styled-system/css';

const styles = {
  title: css({
    fontSize: '1.5rem',
    textAlign: 'center',
    fontWeight: 'bold',
  }),
  text: css({
    margin: '1rem auto 0',
    textAlign: 'center',
  }),
};

type Props = {
  playerNames: string[];
};

export const JinrohFormContent: React.FC<Props> = ({ playerNames }) => {
  return (
    <>
      <p className={styles.title}>いたずら仲間はいるかな・・・？</p>
      {playerNames.length === 0 ? (
        <p className={styles.text}>人狼のプレーヤーはいません😿</p>
      ) : (
        <p className={styles.text}>
          人狼のプレーヤーは{playerNames.join('さんと')}さんです😸
        </p>
      )}
    </>
  );
};
