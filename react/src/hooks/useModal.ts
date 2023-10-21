import { useState } from 'react';

/**
 * @param isOpen 最初から開いておくかどうか
 */
export const useModal = (isOpen: boolean): {
  open: boolean;
  onOpenModal: () => void;
  onCloseModal: () => void;
} => {
  const [open, setOpen] = useState(isOpen);

  const onOpenModal = () => {
    setOpen(true);
  };
  const onCloseModal = () => {
    setOpen(false);
  };

  return {
    open,
    onOpenModal,
    onCloseModal,
  };
};
