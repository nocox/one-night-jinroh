import { Modal as ModalOrigin } from 'react-responsive-modal';
import type { ModalProps } from 'react-responsive-modal';
import './custom-modal.css';
import 'react-responsive-modal/styles.css';

export const Modal: React.FC<ModalProps> = ({ ...props }) => {
  return (
    <ModalOrigin
      center
      showCloseIcon={false}
      classNames={{ modal: 'custom-modal' }}
      {...props}
    >
      {props.children}
    </ModalOrigin>
  );
};
