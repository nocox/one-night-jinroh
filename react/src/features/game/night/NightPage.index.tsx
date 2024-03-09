import { createRoot } from 'react-dom/client';
import { NightPage } from '@/features/game/night/NightPage';

const container = document.getElementById('jsi-entry');
const root = createRoot(container!);

root.render(<NightPage />);
