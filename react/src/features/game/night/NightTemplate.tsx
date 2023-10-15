import { css } from 'styled-system/css';
import { NightActionForm } from './components/NightActionForm';
import { RoleDescription } from './components/RoleDescription';
import { DefaultLayout } from '@/components';
import type { GameIndex } from '@/features/game/type';

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
  gameIndex: GameIndex;
  doneNightAct: boolean;
};

export const NightTemplate: React.FC<Props> = ({ gameIndex, doneNightAct }) => {
  return (
    <DefaultLayout>
      <h2 className={styles.title}>
        {gameIndex.playerName}さん、夜の行動を行ってください
      </h2>

      <RoleDescription roleId={gameIndex.playerRole.roleId} />

      <NightActionForm
        doneNightAct={doneNightAct}
        playerRole={gameIndex.playerRole}
        otherPlayerList={gameIndex.otherPlayerList}
      />
    </DefaultLayout>
  );
};
