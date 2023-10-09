import { createRoot } from 'react-dom/client';
import { VotePage } from '@/features/game/vote/VotePage';

const container = document.getElementById('jsi-entry');
const root = createRoot(container!);

root.render(<VotePage />);
