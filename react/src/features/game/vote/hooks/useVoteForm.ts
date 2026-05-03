import type React from 'react';
import { useState } from 'react';
import { fetchPostWrapper } from '@/api';

export const useVoteForm = (
  votingDestination: number | null,
  setVotingDestination: React.Dispatch<React.SetStateAction<number | null>>,
): {
  selectedPlayerId: number | undefined;
  errorMessage: string;
  handleSelectedPlayerIdChange: (
    e: React.ChangeEvent<HTMLInputElement>,
  ) => void;
  handleVoteSubmit: (
    e: React.MouseEvent<HTMLButtonElement>,
  ) => Promise<void> | void;
} => {
  const [selectedPlayerId, setSelectedPlayerId] = useState<number | undefined>(
    votingDestination ?? undefined,
  );
  const [errorMessage, setErrorMessage] = useState<string>('');

  const handleSelectedPlayerIdChange = (
    e: React.ChangeEvent<HTMLInputElement>,
  ) => {
    setSelectedPlayerId(Number(e.target.value));
  };

  const handleVoteSubmit = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    if (selectedPlayerId === undefined) {
      setErrorMessage('相手を選択してください。');

      return;
    }

    await fetchPostWrapper('/vote', {
      body: { gameParticipantId: selectedPlayerId },
    });
    setVotingDestination(selectedPlayerId);
  };

  return {
    selectedPlayerId,
    errorMessage,
    handleSelectedPlayerIdChange,
    handleVoteSubmit,
  };
};
