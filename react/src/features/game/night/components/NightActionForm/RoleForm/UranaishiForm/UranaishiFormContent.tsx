import { css } from 'styled-system/css';
import { Button } from '@/components';
import type { UranaiStatus } from '@/features/game/night/type';
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
};

type Props = {
  otherPlayerList: OtherPlayer[];
  handleChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  handleSubmit: (e: React.MouseEvent<HTMLButtonElement>) => void;
  selectedPlayerId: number | undefined;
  uranaiStatus: UranaiStatus;
};

export const UranaishiFormContent: React.FC<Props> = ({
  otherPlayerList,
  handleChange,
  handleSubmit,
  selectedPlayerId,
  uranaiStatus,
}) => {
  console.log('selectedPlayerIdだよ：', selectedPlayerId);
  console.log('uranaiStatusだよ：', uranaiStatus);

  return (
    <>
      <p>占うプレーヤーを選んでください</p>

      <ul className={styles.ul}>
        {otherPlayerList.map((player) => (
          <li key={player.id}>
            <label className={styles.label}>
              <input
                type="radio"
                name={player.name}
                value={player.id}
                onChange={handleChange}
                checked={
                  selectedPlayerId === player.id && uranaiStatus === 'PLAYER'
                }
              />
              {player.name}
            </label>
          </li>
        ))}
        <li>
          <label className={styles.label}>
            <input
              type="radio"
              name="おやすみ中のカード"
              value={'HOLIDAY_ROLES'}
              onChange={handleChange}
              checked={
                selectedPlayerId === undefined &&
                uranaiStatus === 'HOLIDAY_ROLES'
              }
            />
            おやすみ中のカード
          </label>
        </li>
      </ul>

      <Button
        type="submit"
        onClick={handleSubmit}
        customStyles={styles.actionButton}
      >
        占う
      </Button>
    </>
  );
};
