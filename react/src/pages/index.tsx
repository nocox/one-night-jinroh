import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { About } from './about';
import { Home } from './home';

const AppRouter: React.FC = () => (
  <Router>
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="about" element={<About />} />
    </Routes>
  </Router>
);

export default AppRouter;
