import { createRoot } from 'react-dom/client';
import { TallyPage } from '@/features/game/tally/TallyPage';

const container = document.getElementById('jsi-entry');
const root = createRoot(container!);

root.render(<TallyPage />);
