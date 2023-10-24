import { css } from 'styled-system/css';
import { HolidayRole } from './components/HolidayRole';
import { Player } from './components/Player';
import { Button, ContentBox, DefaultLayout } from '@/components';
import type {
  GameParticipantWithResultBean,
  JudgeResult,
} from '@/features/game/result/type';
import type { RoleEnglishName } from '@/features/role';

const styles = {
  title: css({
    margin: '1rem auto',
    fontSize: '1.5rem',
    fontWeight: 'bold',
    textAlign: 'center',
  }),
  resultLayout: css({
    display: 'grid',
    gridTemplateColumns: '1fr',
    gap: '1rem',
    md: {
      gridTemplateColumns: '1fr 1fr',
    },
  }),
  contentTitle: css({
    margin: '1rem auto',
    fontSize: '1.2em',
    textAlign: 'center',
  }),
  playerWrapper: css({
    display: 'grid',
    justifyContent: 'center',
    alignItems: 'center',
    gap: '1rem',
  }),
  holidayRoles: css({
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    gap: '1rem',
  }),
  button: css({
    display: 'flex',
    justifyContent: 'center',
    margin: '2rem auto 1rem',
  }),
};

type Props = {
  judgeResult: JudgeResult;
  hostFlag: boolean;
  participants: GameParticipantWithResultBean[];
  holidayRoles: RoleEnglishName[];
  handleReturnRoom: () => void;
};

export const ResultTemplate: React.FC<Props> = ({
  judgeResult,
  hostFlag,
  participants,
  holidayRoles,
  handleReturnRoom,
}) => {
  return (
    <DefaultLayout>
      <h2 className={styles.title}>{judgeResult.text}</h2>
      <div className={styles.resultLayout}>
        <ContentBox>
          <h3 className={styles.contentTitle}>かち</h3>
          <div className={styles.playerWrapper}>
            {participants
              .filter((participant) => {
                return participant.winOrLose === 'win';
              })
              .map((participant, index) => {
                return <Player key={index} participant={participant} />;
              })}
          </div>
        </ContentBox>
        <ContentBox>
          <h3 className={styles.contentTitle}>まけ</h3>
          <div className={styles.playerWrapper}>
            {participants
              .filter((participant) => {
                return participant.winOrLose === 'lose';
              })
              .map((participant, index) => {
                return <Player key={index} participant={participant} />;
              })}
          </div>
        </ContentBox>
        <div className={css({ gridColumn: '1/2', md: { gridColumn: '1/3' } })}>
          <ContentBox>
            <h3 className={styles.contentTitle}>おやすみカード</h3>
            <div className={styles.holidayRoles}>
              {holidayRoles.map((role, index) => {
                return <HolidayRole holidayRole={role} key={index} />;
              })}
            </div>
          </ContentBox>
        </div>
      </div>
      {hostFlag && (
        <div className={styles.button}>
          <Button onClick={handleReturnRoom}>全員ルームに戻す</Button>
        </div>
      )}
    </DefaultLayout>
  );
};
