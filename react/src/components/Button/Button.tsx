import { cx } from 'styled-system/css';
import type { RecipeVariantProps } from 'styled-system/types/recipe';
import { button } from './button.css.ts';

type Props = {
  customStyle?: string;
  children: React.ReactNode;
} & React.ButtonHTMLAttributes<HTMLButtonElement> &
  RecipeVariantProps<typeof button>;

export const Button: React.FC<Props> = ({
  customStyle,
  children,
  color,
  shape,
  size,
  ...props
}: Props) => {
  return (
    <button
      className={cx(button({ color, shape, size }), customStyle ?? '')}
      {...props}
    >
      {children}
    </button>
  );
};
