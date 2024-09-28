import { createRoot } from 'react-dom/client';
import { ErrorBoundary } from 'react-error-boundary';
import { ErrorFallback } from '@/features/error/ErrorFallback';
import { NightPage } from '@/features/game/night/NightPage';

const container = document.getElementById('jsi-entry');
const root = createRoot(container!);

root.render(
  <ErrorBoundary fallback={<ErrorFallback />}>
    <NightPage />
  </ErrorBoundary>,
);
