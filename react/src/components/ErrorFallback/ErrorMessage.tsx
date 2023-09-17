import { css } from 'styled-system/css';

const styles = {
  title: css({
    color: '#fa5252',
    fontSize: '1.25rem',
    fontWeight: 'bold',
    padding: '0.25em',
  }),
};

const createDisplayErrorMessage = (error: Error) => {
  console.log(error); // TODO エラーの種類によって表示するメッセージを変える

  return '予期せぬエラーが発生しました。申し訳ありませんが、時間をおいて再度お試しください。';
};

type Props = {
  error: Error;
};

export const ErrorMessage: React.FC<Props> = ({ error }) => {
  return (
    <>
      <p className={styles.title}>エラーが発生しました。</p>
      <p>{createDisplayErrorMessage(error)}</p>
    </>
  );
};
