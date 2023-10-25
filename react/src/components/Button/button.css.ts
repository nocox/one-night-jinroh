import { cva } from 'styled-system/css';

const blue = '#50a0f6';
const red = '#bd625a';

export const button = cva({
  base: {
    display: 'block',
    textDecoration: 'none',
    cursor: 'pointer',
  },
  variants: {
    fullWidth: {
      true: { width: '100%' },
    },

    radius: {
      xs: { borderRadius: '0' },
      sm: { borderRadius: '0.25rem' },
      md: { borderRadius: '0.5rem' },
      lg: { borderRadius: '1rem' },
      xl: { borderRadius: '2rem' },
    },

    size: {
      xs: { fontSize: '0.75rem', padding: '0 0.875rem', height: '1.75rem' },
      sm: { fontSize: '0.875rem', padding: '0 1.125rem', height: '2.25rem' },
      md: { fontSize: '1rem', padding: '0 1.375rem', height: '2.75rem' },
      lg: { fontSize: '1.125rem', padding: '0 1.625rem', height: '3.25rem' },
      xl: { fontSize: '1.25rem', padding: '0 2rem', height: '3.75rem' },
    },

    buttonType: {
      default: {
        color: '#000',
        backgroundColor: '#F8F9FA',
        border: '1px solid gray',
        _hover: {
          backgroundColor: 'color-mix(in srgb, gray 10%, white 100%)',
        },
      },
      filled: {
        color: '#FFF',
        backgroundColor: 'var(--button-color)',
        _hover: {
          backgroundColor:
            'color-mix(in srgb, var(--button-color) 100%, black 10%)',
        },
      },
      outlined: {
        color: 'var(--button-color)',
        backgroundColor: '#FFF',
        border: '1px solid var(--button-color)',
        _hover: {
          backgroundColor:
            'color-mix(in srgb, var(--button-color) 5%, white 100%)',
        },
      },
    },

    colorType: {
      blue: { '--button-color': `${blue}` },
      red: { '--button-color': `${red}` },
    },

    isDisabled: {
      true: {
        backgroundColor: '#F1F3F5',
        color: '#ADB5BD',
        cursor: 'not-allowed',
        border: 'none',
        _hover: {
          backgroundColor: '#F1F3F5',
        },
      },
    },
  },
  defaultVariants: {
    buttonType: 'outlined',
    colorType: 'blue',
    size: 'md',
    radius: 'xl',
  },
});
