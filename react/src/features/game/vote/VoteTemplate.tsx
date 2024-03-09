import { css } from 'styled-system/css';
import { Players } from '../talk/components/Players';
import { VoteForm } from './components/VoteForm';
import { ContentBox, DefaultLayout } from '@/components';
import { RoleList } from '@/features/game/components';
import type {
  GameParticipant,
  GameParticipantWithCoRole,
} from '@/features/game/type';
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
};

type Props = {
  nightActLog: string | undefined;
  players: GameParticipantWithCoRole[];
  gameRuleList: GameRule[];
  canVotePlayers: GameParticipant[];
  votingDestination: number | undefined;
  setVottingDestination: React.Dispatch<
    React.SetStateAction<number | undefined>
  >;
};

export const VoteTemplate: React.FC<Props> = ({
  nightActLog,
  players,
  gameRuleList,
  canVotePlayers,
  votingDestination,
  setVottingDestination,
}) => {
  return (
    <DefaultLayout>
      <h2 className={styles.title}>
        話し合いが終了しました。投票を行ってください。
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
          <RoleList gameRuleList={gameRuleList} />
        </div>
        <div>
          <VoteForm
            canVotePlayers={canVotePlayers}
            votingDestination={votingDestination}
            setVotingDestination={setVottingDestination}
          />
        </div>
      </div>
    </DefaultLayout>
  );
};
