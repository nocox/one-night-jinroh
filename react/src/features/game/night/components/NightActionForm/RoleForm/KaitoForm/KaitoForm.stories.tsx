import type { Meta, StoryObj } from '@storybook/react';
import { KaitoFormContent } from './KaitoFormContent';

const meta = {
  title:
    'features/game/night/NightActionForm/RoleForm/KaitoForm/KaitoFormContent',
  component: KaitoFormContent,
} satisfies Meta<typeof KaitoFormContent>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {
  args: {
    otherPlayerList: [
      {
        hostFlag: false,
        id: 1,
        name: 'いぬ',
        role: {
          roleId: 1,
          roleName: '村人',
        },
      },
      {
        hostFlag: false,
        id: 2,
        name: 'ねこ',
        role: {
          roleId: 2,
          roleName: '人狼',
        },
      },
    ],
    errorMessage: '',
    selectedPlayerId: undefined,
  },
};

export const WithErrorMessage: Story = {
  args: {
    otherPlayerList: [
      {
        hostFlag: false,
        id: 1,
        name: 'いぬ',
        role: {
          roleId: 1,
          roleName: '村人',
        },
      },
      {
        hostFlag: false,
        id: 2,
        name: 'ねこ',
        role: {
          roleId: 2,
          roleName: '人狼',
        },
      },
    ],
    errorMessage: '交換相手を選択してください。',
    selectedPlayerId: undefined,
  },
};
