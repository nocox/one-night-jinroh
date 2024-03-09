import type { Meta, StoryObj } from '@storybook/react';
import { TalkTemplate } from './TalkTemplate';

const meta = {
  title: 'features/game/talk/TalkTemplate',
  component: TalkTemplate,
} satisfies Meta<typeof TalkTemplate>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {
  args: {
    players: [
      {
        id: 1,
        name: 'ホストのふくろう',
        hostFlag: true,
        role: {
          roleId: 1,
          roleName: '村人',
        },
        co: {
          id: 1,
          role: 'murabito',
        },
      },
      {
        id: 2,
        name: 'いぬ',
        hostFlag: false,
        role: {
          roleId: -1,
          roleName: '不明',
        },
        co: {
          id: 1,
          role: 'murabito',
        },
      },
      {
        id: 3,
        name: 'ねこ',
        hostFlag: false,
        role: {
          roleId: -1,
          roleName: '不明',
        },
        co: {
          id: 1,
          role: 'murabito',
        },
      },
    ],
    nightActLog: '夜の行動ログ',
    hostFlg: true,

    getMyPlayer: () => {
      return {
        hostFlag: false,
        id: 1,
        name: 'ホストのふくろう',
        role: {
          roleId: 1,
          roleName: '村人',
        },
        co: {
          id: 1,
          role: 'murabito',
        },
      };
    },
    gameRuleList: [
      {
        roleId: 1,
        roleName: '村人',
        count: 2,
      },
      {
        roleId: 2,
        roleName: '人狼',
        count: 2,
      },
      {
        roleId: 3,
        roleName: '占い師',
        count: 1,
      },
      {
        roleId: 4,
        roleName: '怪盗',
        count: 1,
      },
      {
        roleId: 5,
        roleName: '狂人',
        count: 1,
      },
      {
        roleId: 6,
        roleName: '吊り人',
        count: 1,
      },
    ],
  },
};
