import type { Meta, StoryObj } from '@storybook/react';
import { UranaishiFormContent } from './UranaishiFormContent';

const meta = {
  title:
    'features/game/night/NightActionForm/RoleForm/UranaishiForm/UranaishiFormContent',
  component: UranaishiFormContent,
} satisfies Meta<typeof UranaishiFormContent>;

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
    selectedPlayerId: undefined,
    uranaiStatus: 'HOLIDAY_ROLES',
  },
};
