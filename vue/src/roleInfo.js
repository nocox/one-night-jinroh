export const getRoleInfo = (key) => {
  if (key in ROLE_NAME_MAPPING) {
    return ROLE_INFO[ROLE_NAME_MAPPING[key]];
  } else {
    if (key in ROLE_INFO) {
      return ROLE_INFO[key];
    } {
      return ROLE_INFO.unknown;
    }
  }
};

export const ROLE_NAME_MAPPING = {
  不明: "unknown",
  人狼: "jinroh",
  村人: "murabito",
  占い師: "uranaishi",
  怪盗: "kaito",
  狂人: "kyojin",
  吊り人: "turibito",
};

export const CO_ROLE_LIST = [
  "jinroh",
  "murabito",
  "uranaishi",
  "kaito",
  "kyojin",
  "turibito",
]

export const ROLE_INFO = {
  unknown: {
    img: require("@/assets/images/card.png"),
    desc: "不明",
    icon: require("@/assets/images/chara-icon/unknown.png"),
  },
  jinroh: {
    img: require("@/assets/images/chara/chara1.png"),
    desc: `他のプレイヤーに人狼がいるかどうか確認できます。`,
    icon: require("@/assets/images/chara-icon/jinroh.png"),
    co: require("@/assets/images/fukidashi/jinroh.png"),
  },
  murabito: {
    img: require("@/assets/images/chara/chara2.png"),
    desc: `夜の行動はありません。`,
    icon: require("@/assets/images/chara-icon/murabito.png"),
    co: require("@/assets/images/fukidashi/murabito.png"),
  },
  uranaishi: {
    img: require("@/assets/images/chara/chara3.png"),
    desc: `『他のプレイヤー』ひとりまたは『おやすみ中のカード』の役職を確認できます。`,
    icon: require("@/assets/images/chara-icon/uranaishi.png"),
    co: require("@/assets/images/fukidashi/uranaishi.png"),
  },
  kaito: {
    img: require("@/assets/images/chara/chara4.png"),
    desc: `『他のプレイヤー』ひとりと役職を交換できます。
            勝利条件は交換後の役職に準じます。`,
    icon: require("@/assets/images/chara-icon/kaito.png"),
    co: require("@/assets/images/fukidashi/kaito.png"),
  },
  kyojin: {
    img: require("@/assets/images/chara/chara5.png"),
    desc: `夜の行動はありません。
              OKボタンを押して夜が明けるのを待ちましょう。`,
    icon: require("@/assets/images/chara-icon/kyojin.png"),
    co: require("@/assets/images/fukidashi/kyojin.png"),
  },
  turibito: {
    img: require("@/assets/images/chara/chara6.png"),
    desc: `夜の行動はありません。
            OKボタンを押して夜が明けるのを待ちましょう。`,
    icon: require("@/assets/images/chara-icon/turibito.png"),
    co: require("@/assets/images/fukidashi/turibito.png"),
  },
};
