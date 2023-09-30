import { css } from 'styled-system/css';
import { Players } from './components/Players';
import type { Player } from './type';
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
  gameId: number;
  players: Player[];
};
export const TalkTemplate: React.FC<Props> = ({ gameId, players }) => {
  console.log(gameId, players);

  return (
    <DefaultLayout>
      <h2 className={styles.title}>
        朝になりました。話し合いを行ってください。
      </h2>

      <Players players={players} />
    </DefaultLayout>
  );
};
