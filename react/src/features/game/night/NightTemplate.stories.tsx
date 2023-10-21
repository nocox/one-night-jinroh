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
    gameIndex: {
      hostFlag: true,
      nightActLog: '夜の行動ログ',
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
      playerId: 1,
      playerName: 'ホストのふくろう',
      playerRole: {
        roleId: 1,
        roleName: '村人',
      },
    },
    doneNightAct: false,
  },
};

export const Jinroh: Story = {
  args: {
    gameIndex: {
      hostFlag: true,
      nightActLog: '夜の行動ログ',
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
      playerId: 1,
      playerName: 'ホストのふくろう',
      playerRole: {
        roleId: 2,
        roleName: '人狼',
      },
    },
    doneNightAct: false,
  },
};

export const Uranaishi: Story = {
  args: {
    gameIndex: {
      hostFlag: true,
      nightActLog: '夜の行動ログ',
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
      playerId: 1,
      playerName: 'ホストのふくろう',
      playerRole: {
        roleId: 3,
        roleName: '占い師',
      },
    },
    doneNightAct: false,
  },
};

export const Kaito: Story = {
  args: {
    gameIndex: {
      hostFlag: true,
      nightActLog: '夜の行動ログ',
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
      playerId: 1,
      playerName: 'ホストのふくろう',
      playerRole: {
        roleId: 4,
        roleName: '怪盗',
      },
    },
    doneNightAct: false,
  },
};

export const Kyojin: Story = {
  args: {
    gameIndex: {
      hostFlag: true,
      nightActLog: '夜の行動ログ',
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
      playerId: 1,
      playerName: 'ホストのふくろう',
      playerRole: {
        roleId: 5,
        roleName: '狂人',
      },
    },
    doneNightAct: false,
  },
};

export const Turibito: Story = {
  args: {
    gameIndex: {
      hostFlag: true,
      nightActLog: '夜の行動ログ',
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
      playerId: 1,
      playerName: 'ホストのふくろう',
      playerRole: {
        roleId: 6,
        roleName: '吊人',
      },
    },
    doneNightAct: false,
  },
};
