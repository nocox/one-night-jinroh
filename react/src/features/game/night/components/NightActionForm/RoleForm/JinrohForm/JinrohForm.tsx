import type React from 'react';
import { useEffect, useState } from 'react';
import { JinrohFormContent } from './JinrohFormContent';
import { fetchGetWrapper } from '@/api';
import type { NightJinrohPlayers } from '@/features/game/night/type';

export const JinrohForm: React.FC = () => {
  const [playerNames, setPlayerNames] = useState<string[]>([]);

  useEffect(() => {
    const fetchNightJinrohPlayersAsync = async () => {
      const result = await fetchGetWrapper<NightJinrohPlayers>(
        '/night/jinroh/index',
      );

      setPlayerNames(result.playerNames);
    };

    void fetchNightJinrohPlayersAsync();
  }, []);

  return <JinrohFormContent playerNames={playerNames} />;
};
