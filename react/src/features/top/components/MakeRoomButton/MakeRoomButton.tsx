import { createRoom } from '../../api';
import makeBtn from './make_room.png';

type Props = {
  className: string;
};

export const MakeRoomButton: React.FC<Props> = ({ className }) => {

  const handleClick = async () => {
    try {
      await createRoom();
      location.href = "/room"
    } catch (error) {
      console.log(error); // TODO: ErrorFallbackを実装する
    }
  };

  return (
    <button className={className} onClick={handleClick}>
      <img src={makeBtn} alt="へやをつくる" />
    </button>
  );
};
