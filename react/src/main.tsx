import React from 'react';
import ReactDOM from 'react-dom/client';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';
import { ErrorBoundary } from 'react-error-boundary';
import { ErrorFallback } from '@/features/error/ErrorFallback';
import AppRouter from './routes/AppRouter';
import './index.css';

const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <QueryClientProvider client={queryClient}>
      <ReactQueryDevtools initialIsOpen={false} />
      <ErrorBoundary FallbackComponent={ErrorFallback}>
        <AppRouter />
      </ErrorBoundary>
    </QueryClientProvider>
  </React.StrictMode>,
);
