import type { Message } from 'webstomp-client';

export type Subscribe = {
  path: string;
  callback: (message?: Message) => void;
};
