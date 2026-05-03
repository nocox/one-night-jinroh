module.exports = {
  extends: ['stylelint-config-standard', 'stylelint-config-recess-order'],
  ignoreFiles: ['**/node_modules/**', '**/styled-system/**', '**/dist/**'],
  plugins: ['stylelint-order'],
};
