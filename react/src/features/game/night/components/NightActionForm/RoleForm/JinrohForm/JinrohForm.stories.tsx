import type { Meta, StoryObj } from '@storybook/react';
import { JinrohFormContent } from './JinrohFormContent';

const meta = {
  title: 'features/game/night/NightActionForm/RoleForm/JinrohForm/JinrohFormContent',
  component: JinrohFormContent,
} satisfies Meta<typeof JinrohFormContent>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {
  args: {
    playerNames: ['いぬ', 'ねこ'],
  },
};

export const NoPlayer: Story = {
  args: {
    playerNames: [],
  },
};