import { css } from 'styled-system/css';
import { ComingOut } from './components/ComingOut';
import { Players } from './components/Players';
import type { Player } from './type';
import { DefaultLayout } from '@/components';
import { RoleList } from '@/features/game/components';

const styles = {
  title: css({
    margin: '1rem auto',
    fontSize: '1.5rem',
    fontWeight: 'bold',
    textAlign: 'center',
  }),
  gameLayout: css({
    display: 'grid',
    gridTemplateColumns: '1fr',
    gap: '1rem',
    md: {
      gridTemplateColumns: '1fr 2fr',
    },
  }),
  player: css({
    gridColumn: '1 / 3',
  }),
};

type Props = {
  gameId: number;
  players: Player[];
  getMyPlayer: () => Player;
};
export const TalkTemplate: React.FC<Props> = ({
  gameId,
  players,
  getMyPlayer,
}) => {
  console.log(gameId, players);

  return (
    <DefaultLayout>
      <h2 className={styles.title}>
        朝になりました。話し合いを行ってください。
      </h2>

      <div className={styles.gameLayout}>
        <div className={styles.player}>
          <Players players={players} />
        </div>
        <div>
          <RoleList />
        </div>
        <div>
          <ComingOut getMyPlayer={getMyPlayer} />
        </div>
      </div>
    </DefaultLayout>
  );
};
