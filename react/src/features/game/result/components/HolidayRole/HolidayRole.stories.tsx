import type { Meta, StoryObj } from '@storybook/react';
import { HolidayRole } from './HolidayRole';

const meta = {
  title: 'features/game/result/HolidayRole',
  component: HolidayRole,
} satisfies Meta<typeof HolidayRole>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Murabito: Story = {
  args: {
    holidayRole: 'murabito',
  },
};
