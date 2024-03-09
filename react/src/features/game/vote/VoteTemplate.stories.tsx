import type { Meta, StoryObj } from '@storybook/react';
import { VoteTemplate } from './VoteTemplate';

const meta = {
  title: 'features/game/vote/VoteTemplate',
  component: VoteTemplate,
} satisfies Meta<typeof VoteTemplate>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {
  args: {
    nightActLog: '夜の行動ログ',
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
    canVotePlayers: [
      {
        hostFlag: false,
        id: 2,
        name: 'いぬ',
        role: {
          roleId: -1,
          roleName: '不明',
        },
      },
      {
        hostFlag: false,
        id: 3,
        name: 'ねこ',
        role: {
          roleId: -1,
          roleName: '不明',
        },
      },
    ],
    votingDestination: undefined,
  },
};
export const DoneVote: Story = {
  args: {
    nightActLog: '夜の行動ログ',
    players: [
      {
        id: 1,
        hostFlag: true,
        name: 'ホストのふくろう',
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
    canVotePlayers: [
      {
        hostFlag: false,
        id: 2,
        name: 'いぬ',
        role: {
          roleId: -1,
          roleName: '不明',
        },
      },
      {
        hostFlag: false,
        id: 3,
        name: 'ねこ',
        role: {
          roleId: -1,
          roleName: '不明',
        },
      },
    ],
    votingDestination: 2,
  },
};
