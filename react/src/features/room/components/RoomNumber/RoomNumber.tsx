import { useState } from 'react';
import { RoomNumberBox } from './RoomNumberBox';

type Props = {
  uuid: string;
};

export const RoomNumber: React.FC<Props> = ({ uuid }) => {
  const [isCopyDone, setIsCopyDone] = useState(false);

  const handleCopy = () => {
    void navigator.clipboard.writeText(uuid);
    setIsCopyDone(true);
  };

  return (
    <RoomNumberBox
      roomNumber={uuid}
      isCopyDone={isCopyDone}
      handleCopy={handleCopy}
    />
  );
};
