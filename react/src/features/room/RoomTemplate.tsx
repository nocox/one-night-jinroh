import { RoomControll } from './components/RoomControll';
import { RoomNumber } from './components/RoomNumber';
import { UserBoard } from './components/UserBoard';
import { DefaultLayout } from '@/components';

export const RoomTemplate: React.FC = () => {
  return (
    <DefaultLayout>
      <RoomNumber />

      <UserBoard />

      <RoomControll />
    </DefaultLayout>
  );
};
