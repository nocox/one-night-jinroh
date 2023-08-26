import makeBtn from './make_room.png';

type Props = {
  className: string;
};

export const MakeRoomButton: React.FC<Props> = ({ className }) => {
  return (
    <button className={className}>
      <img src={makeBtn} alt="へやをつくる" />
    </button>
  );
};
