module.exports = {
    "extends": [
        "stylelint-config-html/vue",
        "stylelint-config-standard",
        "stylelint-config-prettier",
        "stylelint-config-recess-order"
    ],
    "overrides": [
        {
            "files": ["**/*.vue", "**/*.scss"],
        }
    ]
}