import React from 'react';
import { createRoot } from 'react-dom/client';
import { GamePage } from '@/features/game/GamePage';

const container = document.getElementById('jsi-entry');
const root = createRoot(container!);
root.render(
  <React.StrictMode>
    <GamePage />
  </React.StrictMode>,
);
