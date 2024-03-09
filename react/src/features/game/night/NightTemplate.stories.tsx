import type { Meta, StoryObj } from '@storybook/react';
import { NightTemplate } from './NightTemplate';

const meta = {
  title: 'features/game/night/NightTemplate',
  component: NightTemplate,
} satisfies Meta<typeof NightTemplate>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Murabito: Story = {
  args: {
    playerName: 'ホストのふくろう',
    playerRole: {
      roleId: 1,
      roleName: '村人',
    },
    otherPlayerList: [
      {
        hostFlag: false,
        id: 2,
        name: 'いぬ',
        role: {
          roleId: 2,
          roleName: '人狼',
        },
      },
    ],
    doneNightAct: false,
  },
};

export const Jinroh: Story = {
  args: {
    playerName: 'ホストのふくろう',
    playerRole: {
      roleId: 2,
      roleName: '人狼',
    },
    otherPlayerList: [
      {
        hostFlag: false,
        id: 2,
        name: 'いぬ',
        role: {
          roleId: 2,
          roleName: '人狼',
        },
      },
    ],
    doneNightAct: false,
  },
};

export const Uranaishi: Story = {
  args: {
    playerName: 'ホストのふくろう',
    playerRole: {
      roleId: 3,
      roleName: '占い師',
    },
    otherPlayerList: [
      {
        hostFlag: false,
        id: 2,
        name: 'いぬ',
        role: {
          roleId: 2,
          roleName: '人狼',
        },
      },
    ],
    doneNightAct: false,
  },
};

export const Kaito: Story = {
  args: {
    playerName: 'ホストのふくろう',
    playerRole: {
      roleId: 4,
      roleName: '怪盗',
    },
    otherPlayerList: [
      {
        hostFlag: false,
        id: 2,
        name: 'いぬ',
        role: {
          roleId: 2,
          roleName: '人狼',
        },
      },
    ],
    doneNightAct: false,
  },
};

export const Kyojin: Story = {
  args: {
    playerName: 'ホストのふくろう',
    playerRole: {
      roleId: 5,
      roleName: '狂人',
    },
    otherPlayerList: [
      {
        hostFlag: false,
        id: 2,
        name: 'いぬ',
        role: {
          roleId: 2,
          roleName: '人狼',
        },
      },
    ],
    doneNightAct: false,
  },
};

export const Turibito: Story = {
  args: {
    playerName: 'ホストのふくろう',
    playerRole: {
      roleId: 6,
      roleName: '吊人',
    },
    otherPlayerList: [
      {
        hostFlag: false,
        id: 2,
        name: 'いぬ',
        role: {
          roleId: 2,
          roleName: '人狼',
        },
      },
    ],
    doneNightAct: false,
  },
};
