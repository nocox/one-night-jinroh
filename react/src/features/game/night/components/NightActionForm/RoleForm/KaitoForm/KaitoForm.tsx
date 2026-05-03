import type React from 'react';
import { useEffect, useState } from 'react';
import { KaitoFormContent } from './KaitoFormContent';
import { KaitoFormResult } from './KaitoFormResult';
import { fetchGetWrapper, fetchPostWrapper } from '@/api';
import type { NightKaitoResult } from '@/features/game/night/type';
import type { GameParticipant } from '@/features/game/type';

type Props = {
  otherPlayerList: GameParticipant[];
};

export const KaitoForm: React.FC<Props> = ({ otherPlayerList }) => {
  const [selectedPlayerId, setSelectedPlayerId] = useState<number | undefined>(
    undefined,
  );

  const [actLog, setActLog] = useState<string | undefined>(undefined);

  const [errorMessage, setErrorMessage] = useState<string>('');

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSelectedPlayerId(Number(e.target.value));
  };

  const handleSubmit = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    if (selectedPlayerId === undefined) {
      setErrorMessage('相手を選択してください。');

      return;
    }

    const body = { participantId: selectedPlayerId };
    const nightKaitoActionResult = await fetchPostWrapper<NightKaitoResult>(
      '/night/kaito',
      {
        body,
      },
    );
    setActLog(nightKaitoActionResult.actLog);
  };

  useEffect(() => {
    const fetchData = async () => {
      const result = await fetchGetWrapper<NightKaitoResult | null>(
        '/night/kaito',
      );

      if (!result) {
        return;
      }
      setSelectedPlayerId(result.selectedParticipantId);

      const selectedPlayer = otherPlayerList.find(
        (player) => player.id === result.selectedParticipantId,
      );

      if (selectedPlayer === undefined) {
        throw new Error('selectedPlayer not found');
      }

      // FIXME フロントエンドで表示制御できるようにする
      setActLog(result.actLog);
    };

    void fetchData();
  }, [otherPlayerList]);

  return (
    <>
      {actLog === undefined && (
        <KaitoFormContent
          errorMessage={errorMessage}
          otherPlayerList={otherPlayerList}
          handleChange={handleChange}
          handleSubmit={handleSubmit}
          selectedPlayerId={selectedPlayerId}
        />
      )}

      {actLog !== undefined && (
        <KaitoFormResult actLog={actLog} roleBeans={[]} />
      )}
    </>
  );
};
