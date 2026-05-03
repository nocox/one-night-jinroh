import type { Meta, StoryObj } from '@storybook/react';
import { UranaishiFormResult } from './UranaishiFormResult';

const meta = {
  title:
    'features/game/night/NightActionForm/RoleForm/UranaishiForm/UranaishiFormResult',
  component: UranaishiFormResult,
} satisfies Meta<typeof UranaishiFormResult>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Player: Story = {
  args: {
    actLog: 'いぬさんは人狼でした',
    roleBeans: [
      {
        roleId: 2,
        roleName: '人狼',
      },
    ],
  },
};

export const HolidayRole: Story = {
  args: {
    actLog: 'おやすみ中のカードは人狼と村人でした',
    roleBeans: [
      {
        roleId: 1,
        roleName: '村人',
      },
      {
        roleId: 2,
        roleName: '人狼',
      },
    ],
  },
};
