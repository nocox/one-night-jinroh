import {useEffect} from "react";

export const useTermRouter = (
  type: 'GameIndex' | 'TermIsDifferent' | 'NotStared' | undefined,
  term: 'night' | 'talk' | 'vote' | 'tally' | 'result',
): void => {
  useEffect(() => {
    switch (type) {
      case 'GameIndex':
        break;
      case 'TermIsDifferent':
        window.location.href = '/' + term;
        break;
      case 'NotStared':
        window.location.href = '/room';
        break;
      default:
        break;
    }
  }, [term, type]);
};
