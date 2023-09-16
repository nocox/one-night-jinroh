import { DefaultLayout } from '@/components';
import type { GameIndex } from '@/features/game/type';

type Props = {
  doneNightAct: boolean;
  gameId: number;
  gameIndex: GameIndex;
};

export const NightTemplate: React.FC<Props> = ({
  doneNightAct,
  gameId,
  gameIndex,
}) => {
  console.log('gameIndex', gameIndex);
  console.log('doneNightAct', doneNightAct);
  console.log('gameId', gameId);
  // TODO テンプレートの実装を進める

  return (
    <DefaultLayout>
      <h2>{gameIndex.playerName}さん、夜の行動を行ってください</h2>
      あなたの役職は{gameIndex.playerRole.roleName}です。
      ほかのプレイヤーは以下の通りです。
      <ul>
        {gameIndex.otherPlayerList.map((player) => (
          <li key={player.id}>{player.name}</li>
        ))}
      </ul>
    </DefaultLayout>
  );
};
