import type { Meta, StoryObj } from '@storybook/react';
import { TopTemplate } from './TopTemplate';

const meta = {
  title: 'features/top/TopTemplate',
  component: TopTemplate,
} satisfies Meta<typeof TopTemplate>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {};
