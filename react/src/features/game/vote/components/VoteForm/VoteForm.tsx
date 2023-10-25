import { useState } from 'react';
import { css } from 'styled-system/css';
import { Button, ContentBox } from '@/components';
import type { OtherPlayer } from '@/features/game/type';
import { postVoteForm } from '@/features/game/vote/api';

const styles = {
  title: css({
    fontSize: '1.25rem',
    fontWeight: 'bold',
    textAlign: 'center',
    margin: '1rem auto',
  }),
  voteList: css({
    display: 'grid',
    gap: '0.5rem',
    maxWidth: '10rem',
    margin: '0 auto',
  }),
  voteListItem: css({
    display: 'flex',
    alignItems: 'center',
    gap: '0.5rem',
    '&:hover': {
      cursor: 'pointer',
    },
  }),
  voteButtonWrapper: css({
    display: 'flex',
    justifyContent: 'center',
    margin: '1rem auto',
  }),
};

type Props = {
  canVotePlayers: OtherPlayer[];
  votingDestination: number | undefined;
};
export const VoteForm: React.FC<Props> = ({
  canVotePlayers,
  votingDestination,
}) => {
  const [selectedPlayerId, setSelectedPlayerId] = useState<number | undefined>(
    votingDestination,
  );
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

    await postVoteForm({ gameParticipantId: selectedPlayerId });
    // FIXME バックエンドからのレスポンスを受け取りたい(今は何も返していない)
  };

  return (
    <ContentBox>
      <h3 className={styles.title}>投票対象</h3>
      <form>
        <ul className={styles.voteList}>
          {canVotePlayers.map((player) => (
            <li key={player.id}>
              <label className={styles.voteListItem}>
                <input
                  type="radio"
                  name="votingDestination"
                  value={player.id}
                  checked={selectedPlayerId === player.id}
                  onChange={handleChange}
                  disabled={votingDestination !== undefined}
                />
                {player.name}
              </label>
            </li>
          ))}
        </ul>
        <div className={styles.voteButtonWrapper}>
          <Button
            type="submit"
            onClick={handleSubmit}
            disabled={votingDestination !== undefined}
            isDisabled={votingDestination !== undefined}
          >
            投票する
          </Button>
        </div>
        {errorMessage && (
          <p className={css({ color: 'red', textAlign: 'center' })}>
            {errorMessage}
          </p>
        )}
        {votingDestination !== undefined && (
          <p className={css({ textAlign: 'center' })}>
            投票完了！他のプレイヤーが投票するまでまっててね！
          </p>
        )}
      </form>
    </ContentBox>
  );
};
