import { useState } from 'react';

export const useModal = (): {
  open: boolean;
  onOpenModal: () => void;
  onCloseModal: () => void;
} => {
  const [open, setOpen] = useState(false);

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
