import type { Meta, StoryObj } from '@storybook/react';
import { TallyTemplate } from './TallyTemplate';

const meta = {
  title: 'features/game/tally/TallyTemplate',
  component: TallyTemplate,
} satisfies Meta<typeof TallyTemplate>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {
  args: {
    hostFlag: true,
    gameRuleList: [
      { roleId: 1, roleName: '村人', count: 1 },
      { roleId: 2, roleName: '人狼', count: 1 },
      { roleId: 3, roleName: '占い師', count: 1 },
      { roleId: 4, roleName: '怪盗', count: 1 },
      { roleId: 5, roleName: '狂人', count: 1 },
      { roleId: 6, roleName: '吊り人', count: 1 },
    ],
    selectedPlayers: [
      {
        id: 1,
        name: 'ホストのふくろう',
        role: { roleId: 1, roleName: '村人' },
        hostFlag: true,
        voteCount: 2,
      },
    ],
    playersWithVoteCount: [
      {
        id: 1,
        name: 'ホストのふくろう',
        role: { roleId: 1, roleName: '村人' },
        hostFlag: true,
        voteCount: 2,
      },
      {
        id: 2,
        name: 'いぬ',
        role: { roleId: 0, roleName: '不明' },
        hostFlag: false,
        voteCount: 0,
      },
      {
        id: 3,
        name: 'ねこ',
        role: { roleId: 0, roleName: '不明' },
        hostFlag: false,
        voteCount: 0,
      },
    ],
    isPeaceful: false,
    cos: [
      { id: 1, role: 'murabito' },
      { id: 2, role: 'jinroh' },
      { id: 3, role: 'uranaishi' },
    ],
  },
};

export const SelectedManyPlayers: Story = {
  args: {
    hostFlag: true,
    gameRuleList: [
      { roleId: 1, roleName: '村人', count: 1 },
      { roleId: 2, roleName: '人狼', count: 1 },
      { roleId: 3, roleName: '占い師', count: 1 },
      { roleId: 4, roleName: '怪盗', count: 1 },
      { roleId: 5, roleName: '狂人', count: 1 },
      { roleId: 6, roleName: '吊り人', count: 1 },
    ],
    selectedPlayers: [
      {
        id: 1,
        name: 'ホストのふくろう',
        role: { roleId: 1, roleName: '村人' },
        hostFlag: true,
        voteCount: 2,
      },
      {
        id: 2,
        name: 'いぬ',
        role: { roleId: 0, roleName: '不明' },
        hostFlag: true,
        voteCount: 2,
      },
    ],
    playersWithVoteCount: [
      {
        id: 1,
        name: 'ホストのふくろう',
        role: { roleId: 1, roleName: '村人' },
        hostFlag: true,
        voteCount: 2,
      },
      {
        id: 2,
        name: 'いぬ',
        role: { roleId: 0, roleName: '不明' },
        hostFlag: false,
        voteCount: 0,
      },
      {
        id: 3,
        name: 'ねこ',
        role: { roleId: 0, roleName: '不明' },
        hostFlag: false,
        voteCount: 0,
      },
      {
        id: 4,
        name: 'くま',
        role: { roleId: 0, roleName: '不明' },
        hostFlag: false,
        voteCount: 0,
      },
    ],
    isPeaceful: false,
    cos: [
      { id: 1, role: 'murabito' },
      { id: 2, role: 'jinroh' },
      { id: 3, role: 'uranaishi' },
      { id: 4, role: 'kaito' },
    ],
  },
};

export const Peaceful: Story = {
  args: {
    hostFlag: true,
    gameRuleList: [
      { roleId: 1, roleName: '村人', count: 1 },
      { roleId: 2, roleName: '人狼', count: 1 },
      { roleId: 3, roleName: '占い師', count: 1 },
      { roleId: 4, roleName: '怪盗', count: 1 },
      { roleId: 5, roleName: '狂人', count: 1 },
      { roleId: 6, roleName: '吊り人', count: 1 },
    ],
    selectedPlayers: [
      {
        id: 1,
        name: 'ホストのふくろう',
        role: { roleId: 1, roleName: '村人' },
        hostFlag: true,
        voteCount: 2,
      },
      {
        id: 2,
        name: 'いぬ',
        role: { roleId: 0, roleName: '不明' },
        hostFlag: true,
        voteCount: 1,
      },
      {
        id: 3,
        name: 'ねこ',
        role: { roleId: 0, roleName: '不明' },
        hostFlag: true,
        voteCount: 1,
      },
    ],
    playersWithVoteCount: [
      {
        id: 1,
        name: 'ホストのふくろう',
        role: { roleId: 1, roleName: '村人' },
        hostFlag: true,
        voteCount: 2,
      },
      {
        id: 2,
        name: 'いぬ',
        role: { roleId: 0, roleName: '不明' },
        hostFlag: false,
        voteCount: 0,
      },
      {
        id: 3,
        name: 'ねこ',
        role: { roleId: 0, roleName: '不明' },
        hostFlag: false,
        voteCount: 0,
      },
    ],
    isPeaceful: true,
    cos: [
      { id: 1, role: 'murabito' },
      { id: 2, role: 'jinroh' },
      { id: 3, role: 'uranaishi' },
    ],
  },
};
