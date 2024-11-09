import type { Meta, StoryObj } from '@storybook/react';
import { TopTemplate } from './TopTemplate';
import { type FetchJoinedRoomStatus } from './type';

const meta = {
  title: 'features/top/TopTemplate',
  component: TopTemplate,
} satisfies Meta<typeof TopTemplate>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {
  args: {
    joinedRoomStatus: 'NOT_JOINED_ROOM' as FetchJoinedRoomStatus,
    loadingJoinedRoomStatus: false,
    refetchJoinedRoomStatus: () => {
      console.log('refetch');
    },
  },
};
