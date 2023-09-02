/**
 * @fileoverview Custom error classes.
 */

/**
 * @description 網羅性チェック用の例外クラス
 * @see https://typescriptbook.jp/reference/statements/never
 */
export class ExhaustiveError extends Error {
  // eslint-disable-next-line @typescript-eslint/restrict-template-expressions
  constructor(value: never, message = `Unsupported type: ${value}`) {
    super(message);
  }

  static {
    this.prototype.name = 'ExhaustiveError';
  }
}

/**
 * @description APIのレスポンスが不正な場合に投げられる例外クラス
 */
export class InvalidResponseBodyError extends Error {
  static {
    this.prototype.name = 'InvalidResponseBodyError';
  }
}
