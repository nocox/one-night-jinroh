import type { Meta, StoryObj } from '@storybook/react';
import { JudgeModal } from './JudgeModal';
import murabitoWinImagePath from '@/assets/images/result/result1.png';
import jinrohWinImagePath from '@/assets/images/result/result2.png';
import turibitoWinImagePath from '@/assets/images/result/result3.png';

const meta = {
  title: 'features/game/result/JudgeModal',
  component: JudgeModal,
} satisfies Meta<typeof JudgeModal>;

export default meta;
type Story = StoryObj<typeof meta>;

export const SIMPLE_VILLAGE_WIN: Story = {
  args: {
    open: true,
    judgeResult: {
      text: '村人陣営の勝利',
      imagePath: murabitoWinImagePath,
    },
  },
};

export const SIMPLE_JINROH_WIN: Story = {
  args: {
    open: true,
    judgeResult: {
      text: '人狼陣営の勝利',
      imagePath: jinrohWinImagePath,
    },
  },
};

export const SUCCESS_PEACE_VILLAGE: Story = {
  args: {
    open: true,
    judgeResult: {
      text: '平和村成功',
      imagePath: murabitoWinImagePath,
    },
  },
};

export const FAIL_PEACE_VILLAGE: Story = {
  args: {
    open: true,
    judgeResult: {
      text: '平和村失敗',
      imagePath: jinrohWinImagePath,
    },
  },
};

export const SUCCESS_HIDE_JINROH_WIN: Story = {
  args: {
    open: true,
    judgeResult: {
      text: '人狼陣営の勝利（潜伏成功）',
      imagePath: jinrohWinImagePath,
    },
  },
};

export const TURIBITO_WIN: Story = {
  args: {
    open: true,
    judgeResult: {
      text: '吊り人の勝利',
      imagePath: turibitoWinImagePath,
    },
  },
};
