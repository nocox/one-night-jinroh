import { css } from 'styled-system/css';
import { Button, ContentBox } from '@/components';
import type { OtherPlayer } from '@/features/game/type';
import { useVoteForm } from '@/features/game/vote/hooks/useVoteForm';

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
  setVotingDestination: React.Dispatch<
    React.SetStateAction<number | undefined>
  >;
};
export const VoteForm: React.FC<Props> = ({
  canVotePlayers,
  votingDestination,
  setVotingDestination,
}) => {
  const {
    selectedPlayerId,
    handleSelectedPlayerIdChange,
    handleVoteSubmit,
    errorMessage,
  } = useVoteForm(votingDestination, setVotingDestination);

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
                  onChange={handleSelectedPlayerIdChange}
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
            onClick={handleVoteSubmit}
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
