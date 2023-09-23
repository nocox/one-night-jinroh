import spinner from './spinner.gif';
export const Spinner: React.FC = () => {
  return (
    <div>
      <img src={spinner} alt="Loading..." />
    </div>
  );
};
