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
import uranaishiIcon from '@/assets/images/chara-icon/uranaishi.png';

type Character = {
  name: string;
  imgUrl: string;
  iconUrl: string;
};

type CharacterName =
  | 'jinroh'
  | 'murabito'
  | 'uranaishi'
  | 'kaito'
  | 'kyojin'
  | 'tsuribito';

export const characters: Record<CharacterName, Character> = {
  jinroh: {
    name: '人狼',
    imgUrl: jinrohImage,
    iconUrl: jinrohIcon,
  },
  murabito: {
    name: '村人',
    imgUrl: murabitoImage,
    iconUrl: murabitoIcon,
  },
  uranaishi: {
    name: '占い師',
    imgUrl: uranaishiImage,
    iconUrl: uranaishiIcon,
  },
  kaito: {
    name: '怪盗',
    imgUrl: kaitoImage,
    iconUrl: kaitoIcon,
  },
  kyojin: {
    name: '狂人',
    imgUrl: kyojinImage,
    iconUrl: kyojinIcon,
  },
  tsuribito: {
    name: '吊人',
    imgUrl: tsuribitoImage,
    iconUrl: tsuribitoIcon,
  },
};
