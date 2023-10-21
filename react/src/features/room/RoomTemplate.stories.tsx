import type { Meta, StoryObj } from '@storybook/react';
import { RoomTemplate } from './RoomTemplate';

const meta = {
  title: 'features/room/RoomTemplate',
  component: RoomTemplate,
} satisfies Meta<typeof RoomTemplate>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {
  args: {
    roomIndexResponseBody: {
      uuid: '1234567890',
      userList: [
        {
          userId: 1,
          name: 'ホストのふくろう',
          hostFlg: true,
        },
      ],
      hostFlg: true,
      myselfUserId: 1,
    },
  },
};
