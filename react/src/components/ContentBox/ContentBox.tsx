import { css, cx } from 'styled-system/css';

const style = {
  contentBox: css({
    padding: '1rem',
    backgroundColor: '#eee',
  }),
};

type Props = {
  children: React.ReactNode;
  className?: string;
};

export const ContentBox: React.FC<Props> = ({ children, className }) => {
  return (
    <div className={cx(style.contentBox, className ?? '')}>{children}</div>
  );
};
