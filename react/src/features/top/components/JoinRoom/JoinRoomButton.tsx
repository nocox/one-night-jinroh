import joinBtn from './join_room.png';

type Props = {
  className: string;
  onOpenModal: () => void;
};

export const JoinRoomButton: React.FC<Props> = ({ className, onOpenModal }) => {
  return (
    <button className={className} onClick={onOpenModal}>
      <img src={joinBtn} alt="へやにはいる" />
    </button>
  );
};
