import { createRoot } from 'react-dom/client';
import { TalkPage } from '@/features/game/talk/TalkPage';

const container = document.getElementById('jsi-entry');
const root = createRoot(container!);


root.render(<TalkPage />);
