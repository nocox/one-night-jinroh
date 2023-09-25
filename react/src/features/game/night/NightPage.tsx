import { NightTemplate } from './NightTemplate';
import { useNightData } from './hooks/useNightData';
import { Loading } from '@/components';

export const NightPage: React.FC = () => {
  const nightIndexResponseBody = useNightData();

  return nightIndexResponseBody === undefined ? (
    <Loading />
  ) : (
    <>
      <NightTemplate nightIndexResponseBody={nightIndexResponseBody} />
    </>
  );
};
