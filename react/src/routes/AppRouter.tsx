import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { NightPage } from '@/features/game/night/NightPage';
import { RoomPage } from '@/features/room/RoomPage';
import { TopPage } from '@/features/top/TopPage';

const AppRouter: React.FC = () => (
  <Router>
    <Routes>
      <Route path="/" element={<TopPage />} />
      <Route path="/room" element={<RoomPage />} />
      <Route path="/night" element={<NightPage />} />
    </Routes>
  </Router>
);

export default AppRouter;
