import { useEffect, useState } from 'react';
import { KaitoFormContent } from './KaitoFormContent';
import { KaitoFormResult } from './KaitoFormResult';
import {
  fetchNightKaitoActionResult,
  postNightKaitoAction,
} from '@/features/game/night/api';
import type { OtherPlayer } from '@/features/game/type';

type Props = {
  otherPlayerList: OtherPlayer[];
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

    const dto = { participantId: selectedPlayerId };
    const nightKaitoActionResult = await postNightKaitoAction(dto);
    setActLog(nightKaitoActionResult.actLog);
  };

  useEffect(() => {
    async function fetchNightKaitoActionResultAsync() {
      const result = await fetchNightKaitoActionResult!();

      if (result === undefined) {
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
    }

    void fetchNightKaitoActionResultAsync();
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

      {actLog !== undefined && <KaitoFormResult actLog={actLog} roles={[]} />}
    </>
  );
};
