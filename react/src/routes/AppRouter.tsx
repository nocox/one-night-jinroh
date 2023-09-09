import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { RoomPage } from '@/features/room/RoomPage';
import { TopPage } from '@/features/top/TopPage';

const AppRouter: React.FC = () => (
  <Router>
    <Routes>
      <Route path="/" element={<TopPage />} />
      <Route path="/room" element={<RoomPage />} />
    </Routes>
  </Router>
);

export default AppRouter;
