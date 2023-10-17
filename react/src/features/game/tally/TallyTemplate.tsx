import { css } from 'styled-system/css';
import { RoleList } from '@/features/game/components/RoleList';
import { Players } from './components/Players/Players';
import { TallyBox } from './components/TallyBox/TallyBox';
import { Button, DefaultLayout } from '@/components';
import type { GameParticipantWithVoteBean } from '@/features/game/tally/type';
import type { CoBean } from '@/features/game/type';
import type { GameRule } from '@/type';

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
    '& div': {
      height: '100%',
    },
  }),
  nightActLog: css({
    gridColumn: '1 / 2',
    md: {
      gridColumn: '1 / 3',
    },
  }),
  player: css({
    gridColumn: '1 / 2',
    md: {
      gridColumn: '1 / 3',
    },
  }),
  button: css({
    display: 'block',
    margin: '2rem auto 1rem',
  }),
  text: css({
    margin: '1rem auto',
    textAlign: 'center',
  }),
};

type Props = {
  hostFlag: boolean;
  gameRuleList: GameRule[];
  selectedPlayers: GameParticipantWithVoteBean[];
  playersWithVoteCount: GameParticipantWithVoteBean[];
  isPeaceful: boolean;
  cos: CoBean[];
};

export const TallyTemplate: React.FC<Props> = ({
  hostFlag,
  selectedPlayers,
  gameRuleList,
  playersWithVoteCount,
  isPeaceful,
  cos,
}) => {
  return (
    <DefaultLayout>
      {isPeaceful ? (
        <h2 className={styles.title}>平和村が選ばれました。</h2>
      ) : (
        <h2 className={styles.title}>
          選ばれたのは
          <br />
          {selectedPlayers.map((player) => (
            <div key={player.id}>
              <span className={css({ color: '#FF0000' })}>{player.name}</span>
              <br />
            </div>
          ))}
          です。
        </h2>
      )}

      <div className={styles.gameLayout}>
        <div className={styles.nightActLog}>
          <Players
            players={playersWithVoteCount}
            selectedPlayers={selectedPlayers}
            cos={cos}
          />
        </div>
        <div className={styles.player}></div>
        <div>
          <RoleList gameRuleList={gameRuleList} />
        </div>
        <div>
          <TallyBox playersWithVoteCount={playersWithVoteCount}>
            {hostFlag ? (
              <Button customStyles={styles.button} color={'blueFill'}>
                結果ページに移動する
              </Button>
            ) : (
              <p className={styles.text}>
                ホストが結果ページに移動するまでお待ちください。
              </p>
            )}
          </TallyBox>
        </div>
      </div>
    </DefaultLayout>
  );
};
