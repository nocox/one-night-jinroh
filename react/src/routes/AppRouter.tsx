import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { NightPage } from '@/features/game/night/NightPage';
import { TalkPage } from '@/features/game/talk/TalkPage';
import { RoomPage } from '@/features/room/RoomPage';
import { TopPage } from '@/features/top/TopPage';

const AppRouter: React.FC = () => (
  <Router>
    <Routes>
      <Route path="/" element={<TopPage />} />
      <Route path="/room" element={<RoomPage />} />
      <Route path="/night" element={<NightPage />} />
      <Route path="/talk" element={<TalkPage />} />
    </Routes>
  </Router>
);

export default AppRouter;
