import { css } from '../../../styled-system/css';

export const TopTemplate: React.FC = () => {
  return (
    <>
      <div className={css({ fontSize: '2xl', fontWeight: 'bold' })}>
        Hello 🐼!
      </div>
    </>
  );
};
