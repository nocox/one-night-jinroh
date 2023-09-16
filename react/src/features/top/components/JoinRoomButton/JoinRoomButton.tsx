import joinBtn from './join_room.png';

type Props = {
  className: string;
};

export const JoinRoomButton: React.FC<Props> = ({ className }) => {
  return (
    <button className={className}>
      <img src={joinBtn} alt="へやにはいる" />
    </button>
  );
};
