import type { RecipeVariantProps } from 'styled-system/types/recipe';
import { button } from './button.css.ts';

type Props = {
  children: React.ReactNode;
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
  ...props
}: Props) => {
  return (
    <button
      className={button({
        fullWidth,
        buttonType,
        radius,
        colorType,
        size,
        isDisabled,
      })}
      {...props}
    >
      {children}
    </button>
  );
};
