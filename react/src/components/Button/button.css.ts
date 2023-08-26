import { cva } from 'styled-system/css';

export const button = cva({
  base: {
    display: 'inline-block',
    padding: '5px 40px',
    marginTop: '10px',
    color: '#50a0f6',
    textDecoration: 'none',
    cursor: 'pointer',
    backgroundColor: 'white',
    border: '1px solid #50a0f6',
    borderRadius: '30px',
  },
  variants: {
    styleType: {
      filled: { backgroundColor: '#50a0f6', color: 'white' },
    },
  },
});
