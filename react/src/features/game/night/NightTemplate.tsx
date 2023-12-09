import { css } from 'styled-system/css';
import { NightActionForm } from './components/NightActionForm';
import { RoleDescription } from './components/RoleDescription';
import { DefaultLayout } from '@/components';
import type { GameParticipant, RoleBean } from '@/features/game/type';

const styles = {
  title: css({
    fontWeight: 'bold',
    fontSize: '1.25rem',
    textAlign: 'center',
    margin: '1rem 0',
  }),
  roleDescription: css({}),
};

type Props = {
  playerName: string;
  playerRole: RoleBean;
  otherPlayerList: GameParticipant[];
  doneNightAct: boolean;
};

export const NightTemplate: React.FC<Props> = ({
  playerName,
  playerRole,
  otherPlayerList,
  doneNightAct,
}) => {
  return (
    <DefaultLayout>
      <h2 className={styles.title}>
        {playerName}さん、夜の行動を行ってください
      </h2>

      <RoleDescription roleId={playerRole.roleId} />

      <NightActionForm
        doneNightAct={doneNightAct}
        playerRole={playerRole}
        otherPlayerList={otherPlayerList}
      />
    </DefaultLayout>
  );
};
