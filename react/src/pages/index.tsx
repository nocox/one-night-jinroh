import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { About } from './about';
import { TopPage } from './top';

const AppRouter: React.FC = () => (
  <Router>
    <Routes>
      <Route path="/" element={<TopPage />} />
      <Route path="about" element={<About />} />
    </Routes>
  </Router>
);

export default AppRouter;
