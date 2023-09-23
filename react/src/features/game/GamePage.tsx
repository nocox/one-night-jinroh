import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { NightPage } from './night/NightPage';

export const GamePage: React.FC = () => {
  return (
    <>
      <h1>GamePage</h1>
      <Router basename="/game">
        <Routes>
          <Route path="night" element={<NightPage />} />
        </Routes>
      </Router>
    </>
  );
};
