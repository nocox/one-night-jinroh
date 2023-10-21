import { createRoot } from 'react-dom/client';
import { ResultPage } from '@/features/game/result/ResultPage';

const container = document.getElementById('jsi-entry');
const root = createRoot(container!);

root.render(<ResultPage />);
