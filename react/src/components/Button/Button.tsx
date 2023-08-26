import type { RecipeVariantProps } from 'styled-system/types/recipe';
import { button } from './button.css.ts';

type Props = {
  children: React.ReactNode;
} & React.ButtonHTMLAttributes<HTMLButtonElement> &
  RecipeVariantProps<typeof button>;

export const Button: React.FC<Props> = ({
  children,
  styleType,
  ...props
}: Props) => {
  return (
    <button className={button({ styleType })} {...props}>
      {children}
    </button>
  );
};
