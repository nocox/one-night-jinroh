import type React from 'react';
import { css } from 'styled-system/css';
import {fetchPostWrapper} from "@/api";
import { Button } from '@/components';

const reset = async () => {
  await fetchPostWrapper( '/leave-room')
  window.location.href = '/';
};

export const ErrorFallback: React.FC = () => {
  return (
    <div
      role="alert"
      className={css({
        display: 'grid',
        placeItems: 'center',
        placeContent: 'center',
        gap: '1.5rem',
        height: '100vh',
      })}
    >
      <p>ゲームが進行できなくなりました。</p>
      <Button type="reset" onClick={reset}>
        リセットしてトップに戻る
      </Button>
    </div>
  );
};
