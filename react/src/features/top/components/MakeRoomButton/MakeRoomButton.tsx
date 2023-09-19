import { useErrorBoundary } from 'react-error-boundary';
import { useNavigate } from 'react-router-dom';
import { createRoom } from '../../api';
import makeBtn from './make_room.png';

type Props = {
  className: string;
};

export const MakeRoomButton: React.FC<Props> = ({ className }) => {
  const navigate = useNavigate();
  const { showBoundary } = useErrorBoundary();

  const handleClick = async () => {
    try {
      await createRoom();

      navigate('/room');
    } catch (error) {
      showBoundary(error);
    }
  };

  return (
    <button className={className} onClick={handleClick}>
      <img src={makeBtn} alt="へやをつくる" />
    </button>
  );
};
