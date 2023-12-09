import jinrohImage from '@/assets/images/chara/chara1.png';
import murabitoImage from '@/assets/images/chara/chara2.png';
import uranaishiImage from '@/assets/images/chara/chara3.png';
import kaitoImage from '@/assets/images/chara/chara4.png';
import kyojinImage from '@/assets/images/chara/chara5.png';
import tsuribitoImage from '@/assets/images/chara/chara6.png';

import jinrohIcon from '@/assets/images/chara-icon/jinroh.png';
import kaitoIcon from '@/assets/images/chara-icon/kaito.png';
import kyojinIcon from '@/assets/images/chara-icon/kyojin.png';
import murabitoIcon from '@/assets/images/chara-icon/murabito.png';
import tsuribitoIcon from '@/assets/images/chara-icon/turibito.png';
import unknownIcon from '@/assets/images/chara-icon/unknown.png';
import uranaishiIcon from '@/assets/images/chara-icon/uranaishi.png';

import jinrohCoIcon from '@/assets/images/co-icon/jinroh.png';
import kaitoCoIcon from '@/assets/images/co-icon/kaito.png';
import kyojinCoIcon from '@/assets/images/co-icon/kyojin.png';
import murabitoCoIcon from '@/assets/images/co-icon/murabito.png';
import tsuribitoCoIcon from '@/assets/images/co-icon/turibito.png';
import uranaishiCoIcon from '@/assets/images/co-icon/uranaishi.png';

export type RoleEnglishName =
  | 'murabito'
  | 'jinroh'
  | 'uranaishi'
  | 'kaito'
  | 'kyojin'
  | 'turibito'
  | 'unknown';

export type RoleJapaneseName =
  | '村人'
  | '人狼'
  | '占い師'
  | '怪盗'
  | '狂人'
  | '吊人'
  | '不明';

type RoleType = 'murabito' | 'jinroh' | 'turibito';

export type Role = {
  roleId: number;
  roleType: RoleType;
  englishName: RoleEnglishName;
  japaneseName: RoleJapaneseName;
  imgPath: string;
  iconPath: string;
  coIconPath: string;
  nightAction: string;
  description: string;
};

export const getRoleByEnglishName = (englishName: RoleEnglishName): Role => {
  const role = roles.find((role) => role.englishName === englishName);
  if (!role) {
    throw new Error(`role not found. englishName: ${englishName}`);
  }

  return role;
};

export const roles: Role[] = [
  {
    roleId: 1,
    roleType: 'murabito',
    englishName: 'murabito',
    japaneseName: '村人',
    imgPath: murabitoImage,
    iconPath: murabitoIcon,
    coIconPath: murabitoCoIcon,
    nightAction: '夜の行動はありません。',
    description:
      '平和な村に住む無垢でかわいい村人さん。特殊な能力はありません。他プレイヤーの言葉だけをヒントに、誰が人狼か推理しましょう。',
  },
  {
    roleId: 2,
    roleType: 'jinroh',
    englishName: 'jinroh',
    japaneseName: '人狼',
    imgPath: jinrohImage,
    iconPath: jinrohIcon,
    coIconPath: jinrohCoIcon,
    nightAction: '他のプレイヤーに人狼がいるかどうか確認できます。',
    description:
      'イタズラ好きなかわいい人狼さん。 夜の間に他の人狼プレイヤーを確認することができます。',
  },
  {
    roleId: 3,
    roleType: 'murabito',
    englishName: 'uranaishi',
    japaneseName: '占い師',
    imgPath: uranaishiImage,
    iconPath: uranaishiIcon,
    coIconPath: uranaishiCoIcon,
    nightAction:
      '『他のプレイヤー』ひとりまたは『おやすみ中のカード』の役職を確認できます。',
    description:
      'ミステリアスでクールな雰囲気の占い師さん。 夜の間に、他プレイヤーまたは場のカードを選択します。他プレイヤーのカードを選択した場合、そのプレイヤーの役職を確認できます。 場のカードを選択した場合、場にあるカード2枚の役職を確認できます。この行動は怪盗が役職を取り替える前に行われます。',
  },
  {
    roleId: 4,
    roleType: 'murabito',
    englishName: 'kaito',
    japaneseName: '怪盗',
    imgPath: kaitoImage,
    iconPath: kaitoIcon,
    coIconPath: kaitoCoIcon,
    nightAction:
      '『他のプレイヤー』ひとりと役職を交換できます。勝利条件は交換後の役職に準じます。',
    description:
      '狙った獲物のハートを盗む怪盗さん。夜の間に、他プレイヤーのカードを選択すると「自分の役職」と「選択したプレイヤーの役職」を 入れ替えることができます。（入れ替えなくても良い）カードの交換は各自の役職確認後に行われるため、交換されたプレイヤーは 交換されたことを知らないままゲームがスタートします。勝利条件は交換後の役職に準じます。',
  },
  {
    roleId: 5,
    roleType: 'jinroh',
    englishName: 'kyojin',
    japaneseName: '狂人',
    imgPath: kyojinImage,
    iconPath: kyojinIcon,
    coIconPath: kyojinCoIcon,
    nightAction: '夜の行動はありません。',
    description:
      '人狼さんにひそかな思いを寄せる狂人さん。 特殊な能力はありませんが、村人とは陣営が異なり、人狼陣営に所属します。 狂人が吊られても人狼陣営の敗北にはなりません。 プレイヤーに人狼がいない平和村の場合、勝利条件は「人間 SIDE」となります(村人と同じ扱いとなります)。',
  },
  {
    roleId: 6,
    roleType: 'turibito',
    englishName: 'turibito',
    japaneseName: '吊人',
    imgPath: tsuribitoImage,
    iconPath: tsuribitoIcon,
    coIconPath: tsuribitoCoIcon,
    nightAction: '夜の行動はありません。',
    description:
      '丈夫な縄を自分の首にかけてぶら下がる不思議な遊びが大好きな吊人さん。 吊られるのが目的です。吊られると一人勝ちとなります。',
  },
];

export const unknownRole: Pick<
  Role,
  'englishName' | 'japaneseName' | 'iconPath'
> = {
  englishName: 'unknown',
  japaneseName: '不明',
  iconPath: unknownIcon,
};
