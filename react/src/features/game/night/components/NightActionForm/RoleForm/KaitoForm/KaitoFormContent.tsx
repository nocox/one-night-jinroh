import { css } from 'styled-system/css';
import { Button } from '@/components';
import type { OtherPlayer } from '@/features/game/type';

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
    margin: '3rem auto 0',
  }),
  actLog: css({
    margin: '1rem auto 0',
  }),
};

type Props = {
  errorMessage: string;
  otherPlayerList: OtherPlayer[];
  handleChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  handleSubmit: (e: React.MouseEvent<HTMLButtonElement>) => void;
  selectedPlayerId: number | undefined;
  actLog: string | undefined;
};

export const KaitoFormContent: React.FC<Props> = ({
  errorMessage,
  otherPlayerList,
  handleChange,
  handleSubmit,
  selectedPlayerId,
  actLog,
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
                disabled={actLog !== undefined}
              />
              {player.name}
            </label>
          </li>
        ))}
      </ul>

      <Button
        type="submit"
        onClick={handleSubmit}
        disabled={actLog !== undefined}
        isDisabled={actLog !== undefined}
        customStyles={styles.actionButton}
      >
        選んだ相手と入れ替える
      </Button>
      {errorMessage && <p>{errorMessage}</p>}

      {actLog !== undefined && <p className={styles.actLog}>{actLog}</p>}
    </>
  );
};
