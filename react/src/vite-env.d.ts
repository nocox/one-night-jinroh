/// <reference types="vite/client" />

/**
 * ビルド済みのsockjs-clientを使うための型定義を追加する
 * 使い方： import SockJS from 'sockjs-client/dist/sockjs';
 * @see https://github.com/sockjs/sockjs-client/issues/439#issuecomment-1177124768
 */
declare module 'sockjs-client/dist/sockjs' {
  export default (await import('sockjs-client')).default;
}
