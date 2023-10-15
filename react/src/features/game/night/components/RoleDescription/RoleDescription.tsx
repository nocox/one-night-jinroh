import { RoleDescriptionBox } from './RoleDescriptionBox';
import { roles } from '@/features/role';

type Props = {
  roleId: number;
};

export const RoleDescription: React.FC<Props> = ({ roleId }) => {
  const role = roles.find((c) => c.roleId === roleId);
  if (!role) {
    // TODO エラー画面を表示する. 必要に応じてカスタムエラークラスを作成する.
    throw new Error('role not found');
  }

  return <RoleDescriptionBox role={role} />;
};
