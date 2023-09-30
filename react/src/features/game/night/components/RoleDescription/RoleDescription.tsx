import { RoleDescriptionBox } from './RoleDescriptionBox';
import { characters } from '@/features/game/character';

type Props = {
  roleId: number;
};

export const RoleDescription: React.FC<Props> = ({ roleId }) => {
  const character = characters.find((c) => c.roleId === roleId);
  if (!character) {
    // TODO エラー画面を表示する. 必要に応じてカスタムエラークラスを作成する.
    throw new Error('character not found');
  }

  return <RoleDescriptionBox character={character} />;
};
