import { css } from 'styled-system/css';
import { Button } from '@/components';
import type { GameParticipant } from '@/features/game/type';

const styles = {
  ul: css({
    display: 'grid',
    gap: '0.5rem',
    margin: '1rem auto 0',
  }),
  label: css({
    maxWidth: '20rem',
    display: 'flex',
    alignItems: 'center',
    gap: '0.25rem',
    '&:hover': {
      cursor: 'pointer',
    },
  }),
  actionButton: css({
    display: 'flex',
    justifyContent: 'center',
    margin: '3rem auto 0',
  }),
};

type Props = {
  errorMessage: string;
  otherPlayerList: GameParticipant[];
  handleChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  handleSubmit: (e: React.MouseEvent<HTMLButtonElement>) => void;
  selectedPlayerId: number | undefined;
};

export const KaitoFormContent: React.FC<Props> = ({
  errorMessage,
  otherPlayerList,
  handleChange,
  handleSubmit,
  selectedPlayerId,
}) => {
  return (
    <>
      <p>役職を入れ替える相手を選んでください。</p>

      <ul className={styles.ul}>
        {otherPlayerList.map((player) => (
          <li key={player.id}>
            <label className={styles.label}>
              <input
                type="radio"
                name={player.name}
                value={player.id}
                onChange={handleChange}
                checked={selectedPlayerId === player.id}
              />
              {player.name}
            </label>
          </li>
        ))}
      </ul>

      <div className={styles.actionButton}>
        <Button type="submit" onClick={handleSubmit}>
          選んだ相手と入れ替える
        </Button>
      </div>
      {errorMessage && <p>{errorMessage}</p>}
    </>
  );
};
