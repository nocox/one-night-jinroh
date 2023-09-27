import { css } from 'styled-system/css';

type Props = {
  playerNames: string[];
};

export const JinrohFormContent: React.FC<Props> = ({ playerNames }) => {
  return (
    <>
      <p
        className={css({
          fontSize: '1.5rem',
          textAlign: 'center',
          fontWeight: 'bold',
        })}
      >
        いたずら仲間はいるかな・・・？
      </p>
      {playerNames.length === 0 ? (
        <p>人狼のプレーヤーはいません😿</p>
      ) : (
        <p
          className={css({
            margin: '1rem auto 0',
            textAlign: 'center',
          })}
        >
          人狼のプレーヤーは{playerNames.join('さんと')}さんです😸
        </p>
      )}
    </>
  );
};
