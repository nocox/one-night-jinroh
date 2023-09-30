import { css } from 'styled-system/css';
import type { TalkIndexResponseBody } from './type';
import { DefaultLayout } from '@/components';

const styles = {
  title: css({
    margin: '1rem auto',
    fontSize: '1.5rem',
    fontWeight: 'bold',
    textAlign: 'center',
  }),
};

type Props = {
  talkIndex: TalkIndexResponseBody;
};
export const TalkTemplate: React.FC<Props> = ({ talkIndex }) => {
  console.log(talkIndex);

  return (
    <DefaultLayout>
      <h2 className={styles.title}>
        朝になりました。話し合いを行ってください。
      </h2>
    </DefaultLayout>
  );
};
