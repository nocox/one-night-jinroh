import { useState } from 'react';
import { NightActionFormBox } from './NightActionFormBox';
import { KaitoForm, UranaishiForm, JinrohForm } from './RoleForm';
import { fetchDoneNightAct } from '@/features/game/night/api';
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
  const [isDoneNightAct, setIsDoneNightAct] = useState<boolean>(doneNightAct);

  function getRoleComponent() {
    switch (playerRole.roleName) {
      case '怪盗':
        return <KaitoForm otherPlayerList={otherPlayerList} />;
      case '占い師':
        return <UranaishiForm otherPlayerList={otherPlayerList} />;
      case '人狼':
        return <JinrohForm />;
      default:
        return <p>夜の行動はありません。</p>;
    }
  }

  const handleDoneNightAct = async () => {
    setIsDoneNightAct(await fetchDoneNightAct());
  };

  return (
    <NightActionFormBox
      doneNightAct={isDoneNightAct}
      handleDoneNightAct={handleDoneNightAct}
    >
      {getRoleComponent()}
    </NightActionFormBox>
  );
};
