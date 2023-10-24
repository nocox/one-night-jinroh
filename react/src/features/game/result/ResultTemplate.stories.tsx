import type { Meta, StoryObj } from '@storybook/react';
import { ResultTemplate } from './ResultTemplate';
import murabitoWinImagePath from '@/assets/images/result/result1.png';
import jinrohWinImagePath from '@/assets/images/result/result2.png';
import turibitoWinImagePath from '@/assets/images/result/result3.png';

const meta = {
  title: 'features/game/result/ResultTemplate',
  component: ResultTemplate,
} satisfies Meta<typeof ResultTemplate>;

export default meta;
type Story = StoryObj<typeof meta>;

export const MurabitWin: Story = {
  args: {
    hostFlag: true,
    participants: [
      {
        playerName: 'ホストのふくろう',
        role: 'murabito',
        coRole: 'murabito',
        winOrLose: 'win',
        myself: true,
        comment: 'コメント',
      },
      {
        playerName: 'いぬ',
        role: 'jinroh',
        coRole: 'murabito',
        winOrLose: 'lose',
        myself: true,
        comment: 'コメント',
      },
    ],
    holidayRoles: ['jinroh', 'turibito'],
    judgeResult: {
      text: '村人陣営の勝利',
      imagePath: murabitoWinImagePath,
    },
  },
};
export const JinrohWin: Story = {
  args: {
    hostFlag: true,
    participants: [
      {
        playerName: 'ホストのふくろう',
        role: 'murabito',
        coRole: 'murabito',
        winOrLose: 'win',
        myself: true,
        comment: 'コメント',
      },
      {
        playerName: 'いぬ',
        role: 'jinroh',
        coRole: 'murabito',
        winOrLose: 'lose',
        myself: true,
        comment: 'コメント',
      },
    ],
    holidayRoles: ['jinroh', 'turibito'],
    judgeResult: {
      text: '人狼陣営の勝利',
      imagePath: jinrohWinImagePath,
    },
  },
};
export const TuribitoWin: Story = {
  args: {
    hostFlag: true,
    participants: [
      {
        playerName: 'ホストのふくろう',
        role: 'murabito',
        coRole: 'murabito',
        winOrLose: 'win',
        myself: true,
        comment: 'コメント',
      },
      {
        playerName: 'いぬ',
        role: 'jinroh',
        coRole: 'murabito',
        winOrLose: 'lose',
        myself: true,
        comment: 'コメント',
      },
    ],
    holidayRoles: ['jinroh', 'turibito'],
    judgeResult: {
      text: '吊り人陣営の勝利',
      imagePath: turibitoWinImagePath,
    },
  },
};
