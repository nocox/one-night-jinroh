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

export type Character = {
  roleId: number;
  EnglishName: string;
  JapaneseName: string;
  imgUrl: string;
  iconUrl: string;
  coIconUrl: string;
  nightAction: string;
};

export const characters: Character[] = [
  {
    roleId: 1,
    EnglishName: 'murabito',
    JapaneseName: '村人',
    imgUrl: murabitoImage,
    iconUrl: murabitoIcon,
    coIconUrl: murabitoCoIcon,
    nightAction: '夜の行動はありません。',
  },
  {
    roleId: 2,
    EnglishName: 'jinroh',
    JapaneseName: '人狼',
    imgUrl: jinrohImage,
    iconUrl: jinrohIcon,
    coIconUrl: jinrohCoIcon,
    nightAction: '他のプレイヤーに人狼がいるかどうか確認できます。',
  },
  {
    roleId: 3,
    EnglishName: 'uranaishi',
    JapaneseName: '占い師',
    imgUrl: uranaishiImage,
    iconUrl: uranaishiIcon,
    coIconUrl: uranaishiCoIcon,
    nightAction:
      '『他のプレイヤー』ひとりまたは『おやすみ中のカード』の役職を確認できます。',
  },
  {
    roleId: 4,
    EnglishName: 'kaito',
    JapaneseName: '怪盗',
    imgUrl: kaitoImage,
    iconUrl: kaitoIcon,
    coIconUrl: kaitoCoIcon,
    nightAction:
      '『他のプレイヤー』ひとりと役職を交換できます。勝利条件は交換後の役職に準じます。',
  },
  {
    roleId: 5,
    EnglishName: 'kyojin',
    JapaneseName: '狂人',
    imgUrl: kyojinImage,
    iconUrl: kyojinIcon,
    coIconUrl: kyojinCoIcon,
    nightAction: '夜の行動はありません。',
  },
  {
    roleId: 6,
    EnglishName: 'tsuribito',
    JapaneseName: '吊人',
    imgUrl: tsuribitoImage,
    iconUrl: tsuribitoIcon,
    coIconUrl: tsuribitoCoIcon,
    nightAction: '夜の行動はありません。',
  },
];

export const unknownCharacter: Pick<
  Character,
  'EnglishName' | 'JapaneseName' | 'iconUrl'
> = {
  EnglishName: 'unknown',
  JapaneseName: '不明',
  iconUrl: unknownIcon,
};
