import { css } from 'styled-system/css';
import { NightActionForm } from './components/NightActionForm';
import { RoleDescription } from './components/RoleDescription';
import type { NightIndexResponseBody } from './type';
import { DefaultLayout } from '@/components';

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
  nightIndexResponseBody: NightIndexResponseBody;
};

export const NightTemplate: React.FC<Props> = ({ nightIndexResponseBody }) => {
  const { gameIndex, doneNightAct } = nightIndexResponseBody;

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
