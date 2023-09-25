import { NightActionFormBox } from './NightActionFormBox';
import { KaitoForm, UranaishiForm, JinrohForm } from './RoleForm';
import type { OtherPlayer, Role } from '@/features/game/type';

type Props = {
  doneNightAct: boolean;
  playerRole: Role;
  otherPlayerList: OtherPlayer[];
};

export const NightActionForm: React.FC<Props> = ({
  doneNightAct,
  playerRole,
  otherPlayerList,
}) => {
  function getRoleComponent() {
    switch (playerRole.roleName) {
      case '怪盗':
        return (
          <KaitoForm
            otherPlayerList={otherPlayerList}
            doneNightAct={doneNightAct}
          />
        );
      case '占い師':
        return <UranaishiForm />;
      case '人狼':
        return <JinrohForm />;
      default:
        return <p>夜の行動はありません。</p>;
    }
  }

  return (
    <NightActionFormBox doneNightAct={doneNightAct}>
      {getRoleComponent()}
    </NightActionFormBox>
  );
};
