import type React from 'react';
import { css } from 'styled-system/css';
import { Button } from '@/components';
import { JINROH_API_BASE_URL } from '@/url';

const reset = async () => {
  const res = await fetch(JINROH_API_BASE_URL + '/exit-room', {
    method: 'POST',
    credentials: 'include',
  });

  if (!res.ok) {
    throw new Error('Failed to exit room');
  }

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
