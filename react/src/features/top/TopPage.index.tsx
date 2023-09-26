import { createRoot } from 'react-dom/client';
import { TopPage } from './TopPage';

const container = document.getElementById('jsi-entry');
const root = createRoot(container!);

root.render(<TopPage />);
