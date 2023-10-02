import { css } from 'styled-system/css';
import { postEndTalk } from './api';
import { ComingOut } from './components/ComingOut';
import { Players } from './components/Players';
import type { Player } from './type';
import { Button, ContentBox, DefaultLayout } from '@/components';
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
  endTalkButton: css({
    margin: '2rem auto',
    display: 'flex',
    justifyContent: 'center',
  }),
};

const handleEndTalk = async () => {
  await postEndTalk();
};

type Props = {
  players: Player[];
  nightActLog: string | undefined;
  hostFlg: boolean;
  getMyPlayer: () => Player;
};
export const TalkTemplate: React.FC<Props> = ({
  players,
  nightActLog,
  hostFlg,
  getMyPlayer,
}) => {
  console.log('nightActLog;', nightActLog, typeof nightActLog);

  return (
    <DefaultLayout>
      <h2 className={styles.title}>
        朝になりました。話し合いを行ってください。
      </h2>

      <div className={styles.gameLayout}>
        <div className={styles.nightActLog}>
          <ContentBox>
            {nightActLog !== undefined && nightActLog !== ''
              ? nightActLog
              : 'あなたの役職に夜の行動はありません'}
          </ContentBox>
        </div>
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

      {hostFlg ? (
        <div className={styles.endTalkButton}>
          <Button onClick={handleEndTalk}>話し合いを終了して投票へ</Button>
        </div>
      ) : (
        <div className={styles.endTalkButton}>
          ホストが話し合いを終了すると投票へ進みます。
        </div>
      )}
    </DefaultLayout>
  );
};
