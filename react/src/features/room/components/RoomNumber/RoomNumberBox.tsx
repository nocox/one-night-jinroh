import { css } from 'styled-system/css';
import copyIcon from './copy-icon.png';

const styles = {
  root: css({
    display: 'grid',
    justifyItems: 'center',
    margin: '1em 0',
  }),
  title: css({
    fontSize: '1.25rem',
    fontWeight: 'bold',
    textAlign: 'center',
  }),

  inputWrapper: css({
    display: 'flex',
    margin: '0.5rem 0',
    gap: '0.5rem',
  }),
  input: css({
    width: '100%',
    border: '1px solid #ccc',
    borderRadius: '4px',
    padding: '0.2em',
  }),
  copyButton: css({
    _hover: {
      cursor: 'pointer',
    },
  }),
};

type Props = {
  roomNumber: string;
  handleCopy: () => void;
  isCopyDone: boolean;
};

export const RoomNumberBox: React.FC<Props> = ({
  roomNumber,
  handleCopy,
  isCopyDone,
}) => {
  return (
    <section className={styles.root}>
      <h2 className={styles.title}>へやばんごう</h2>
      <div className={styles.inputWrapper}>
        <input
          type="text"
          readOnly
          className={styles.input}
          value={roomNumber}
        />
        <button
          type="button"
          className={styles.copyButton}
          onClick={handleCopy}
        >
          <img src={copyIcon} alt="copy room number to clipboard" />
        </button>
      </div>
      <p>この番号を友達に教えてあげてね！</p>
      {isCopyDone && <p>コピーできました！</p>}
    </section>
  );
};
