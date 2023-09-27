import { useEffect, useState } from 'react';
import { JinrohFormContent } from './JinrohFormContent';
import { fetchNightJinrohPlayers } from '@/features/game/night/api';

export const JinrohForm: React.FC = () => {
  const [playerNames, setPlayerNames] = useState<string[]>([]);

  useEffect(() => {
    const fetchNightJinrohPlayersAsync = async () => {
      const result = await fetchNightJinrohPlayers();

      setPlayerNames(result.playerNames);
    };

    void fetchNightJinrohPlayersAsync();
  }, []);

  return <JinrohFormContent playerNames={playerNames} />;
};
