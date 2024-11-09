import {css} from "../../../../../styled-system/css";
import { type FetchJoinedRoomStatus } from '@/features/top/type';

const styles = {
  joinedButtonWrapper: css({
    flex: 1
  }),
}

type Props = {
  className: string;
  joinedRoomStatus: FetchJoinedRoomStatus;
  loading: boolean;
};

export const BackToRoomButton: React.FC<Props> = ({
  joinedRoomStatus,
  loading,
}) => {
  if (loading) {
    <div>loading...</div>;
  }

  if (joinedRoomStatus === 'NOT_JOINED_ROOM') {
    return <></>;
  } else {
    return (
      <button
        className={styles.joinedButtonWrapper}
        onClick={() => {
          location.href = '/room';
        }}
      >
        直前の部屋に再入室する
      </button>
    );
  }
};
