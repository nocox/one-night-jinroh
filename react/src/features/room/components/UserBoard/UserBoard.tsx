import { css } from 'styled-system/css';
import type { User } from '../../type';

const styles = {
  root: css({
    display: 'grid',
    justifyItems: 'center',
    backgroundImage: `url(/image/room-top-bg.png)`,
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    backgroundPosition: 'right bottom',
    color: '#eee',
    maxWidth: '600px',
    minHeight: '24rem',
    margin: '1rem auto',
  }),
  title: css({
    fontSize: '1.25rem',
    fontWeight: 'bold',
    alignSelf: 'end',
  }),
  userList: css({
    padding: '1rem 0 0',
  }),
  annotation: css({
    display: 'flex',
    flexDirection: 'column',
    gap: '0.5rem',
    fontSize: '0.8rem',
    textAlign: 'center',
  }),
};

type Props = {
  myselfUserId: number;
  userList: User[];
};

export const UserBoard: React.FC<Props> = ({ myselfUserId, userList }) => {
  return (
    <section className={styles.root}>
      <h2 className={styles.title}>さんかしゃ</h2>
      <ul className={styles.userList}>
        {userList.map((user) => {
          return (
            <li key={user.userId}>
              {user.name}{' '}
              {user.userId === myselfUserId ? <span>(あなた)</span> : ''}
            </li>
          );
        })}
      </ul>
      <div className={styles.annotation}>
        <p>
          ページをリロードすると
          <br />
          最新の参加者を取得できます。
        </p>
        <p>参加者が3人以上揃うとゲームを開始できます。</p>
      </div>
    </section>
  );
};
