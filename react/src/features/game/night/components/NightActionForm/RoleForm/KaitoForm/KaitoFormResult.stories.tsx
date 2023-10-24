import type { Meta, StoryObj } from '@storybook/react';
import { KaitoFormResult } from './KaitoFormResult';

const meta = {
  title:
    'features/game/night/NightActionForm/RoleForm/KaitoForm/KaitoFormResult',
  component: KaitoFormResult,
} satisfies Meta<typeof KaitoFormResult>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {
  args: {
    actLog: '怪盗の結果: いぬと役職を交換しました．現在の役職はあなたが人狼で，いぬが怪盗です．',
    roleBeans: [
      {
        roleId: 2,
        roleName: '人狼',
      },
    ],
  },
};
