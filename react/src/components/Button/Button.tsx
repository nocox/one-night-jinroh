import { cx } from 'styled-system/css';
import type { RecipeVariantProps } from 'styled-system/types/recipe';
import { button } from './button.css.ts';

type Props = {
  children: React.ReactNode;
  customStyles?: string;
} & React.ButtonHTMLAttributes<HTMLButtonElement> &
  RecipeVariantProps<typeof button>;

export const Button: React.FC<Props> = ({
  children,
  fullWidth,
  buttonType,
  colorType,
  radius,
  size,
  isDisabled,
  customStyles,
  ...props
}: Props) => {
  return (
    <button
      className={cx(
        button({ fullWidth, buttonType, radius, colorType, size, isDisabled }),
        customStyles ?? '',
      )}
      {...props}
    >
      {children}
    </button>
  );
};
