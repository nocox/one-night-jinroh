import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { NightPage } from '@/features/game/night/NightPage';
import { ResultPage } from '@/features/game/result/ResultPage';
import { TalkPage } from '@/features/game/talk/TalkPage';
import { TallyPage } from '@/features/game/tally/TallyPage';
import { VotePage } from '@/features/game/vote/VotePage';
import { RoomPage } from '@/features/room/RoomPage';
import { TopPage } from '@/features/top/TopPage';

const AppRouter: React.FC = () => (
  <Router>
    <Routes>
      <Route path="/" element={<TopPage />} />
      <Route path="/room" element={<RoomPage />} />
      <Route path="/night" element={<NightPage />} />
      <Route path="/talk" element={<TalkPage />} />
      <Route path="/vote" element={<VotePage />} />
      <Route path="/tally" element={<TallyPage />} />
      <Route path="/result" element={<ResultPage />} />
    </Routes>
  </Router>
);

export default AppRouter;
