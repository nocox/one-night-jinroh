import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { TopPage } from '../features/top/TopPage';

const AppRouter: React.FC = () => (
  <Router>
    <Routes>
      <Route path="/" element={<TopPage />} />
    </Routes>
  </Router>
);

export default AppRouter;
