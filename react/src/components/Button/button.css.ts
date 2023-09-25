import { cva } from 'styled-system/css';

const blue = '#50a0f6';
const red = '#bd625a';

export const button = cva({
  base: {
    textDecoration: 'none',
    cursor: 'pointer',
  },
  variants: {
    size: {
      medium: { padding: '0.5rem 2rem' },
      large: { padding: '1rem 2rem' },
    },

    color: {
      blueFill: { color: '#fff', backgroundColor: `${blue}` },
      blueBorder: { color: `${blue}`, border: `1px solid ${blue}` },
      redFill: { color: '#fff', backgroundColor: `${red}` },
      redBorder: { color: `${red}`, border: `1px solid ${red}` },
    },

    shape: {
      round: { borderRadius: '10px' },
      rounder: { borderRadius: '30px' },
    },

    isDisabled: {
      true: { color: '#fff', backgroundColor: "#ccc", border: 'none', cursor: 'default' },
    }
  },
  defaultVariants: {
    size: 'medium',
    color: 'blueBorder',
    shape: 'rounder',
  },
});
