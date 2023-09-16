import { css } from 'styled-system/css';
import { DefaultLayout } from '../../components';

import { JoinRoomButton } from './components/JoinRoomButton';
import { MakeRoomButton } from './components/MakeRoomButton';
import { NewsField } from './components/NewsField';
import { RoleExplanation } from './components/RoleExplanation';
import { TitleCharacters } from './components/TitleCharacters';
import { TitleLogo } from './components/TitleLogo';
import { characters } from '@/character';

const styles = {
  roomButtonWrapper: css({
    display: 'flex',
    justifyContent: 'center',
    gap: '2rem',
    margin: '1rem auto',
  }),
  roomButton: css({
    maxWidth: '170px',
    transition: 'transform 0.2s',
    _hover: {
      cursor: 'pointer',
      transform: 'scale(1.05)',
    },
  }),
  sectionTitle: css({
    fontSize: '1.5rem',
    fontWeight: 'bold',
    padding: '0.25em',
  }),
  sectionContents: css({
    padding: '0.5rem 1rem',
  }),
};

export const TopTemplate: React.FC = () => {
  return (
    <DefaultLayout>
      <h1>
        <TitleLogo />
      </h1>

      <div className={styles.roomButtonWrapper}>
        <MakeRoomButton className={styles.roomButton} />
        <JoinRoomButton className={styles.roomButton} />
      </div>

      <TitleCharacters />

      <section>
        <h2 className={styles.sectionTitle}>お知らせ</h2>
        <div className={styles.sectionContents}>
          <NewsField />
        </div>
      </section>

      <section>
        <h2 className={styles.sectionTitle}>ゆるふわじんろうとは?</h2>
        <div className={styles.sectionContents}>
          <p>
            殺伐とした心理戦を楽しむ「人狼ゲーム」をかわいいキャラ、シンプルなルールにアレンジしたゲームです。
          </p>
          <p>3～7人でふわっと簡単に短時間で遊べます。</p>
        </div>
      </section>

      <section>
        <h2 className={styles.sectionTitle}>世界観</h2>
        <div className={styles.sectionContents}>
          <p>
            人間と人狼が仲良く暮らすゆるふわ村。
            <br />
            この村では夜な夜なこっそり誰かにイタズラしては騒動を起こすいたずらオオカミ娘がおりました。
            <br />
            毎晩のイタズラに困り果てた住人たちは、イタズラの犯人を探し出すために会議を開くことになります。
          </p>
        </div>
      </section>

      <section>
        <h2 className={styles.sectionTitle}>あそびかた</h2>
        <div className={styles.sectionContents}>
          <p>
            本ゲームはPC、スマートフォン両ブラウザで遊ぶことができます。
            <br />
            通常の人狼ゲームとは異なり、襲撃や処刑による途中脱落はなく、一晩で1ゲームが決着するシンプルルールになっています。
          </p>
          <p>
            各プレイヤーは6つの役職のうち、ひとつがランダムに割り振られ、各役職に対応する陣営のプレイヤーとして、それぞれの陣営ごとの勝利条件を目指します。
          </p>
          <p>各役職と対応する陣営は以下の通りです。</p>

          <RoleExplanation
            roleTitle="村人陣営"
            backgroundColor="#b7ecb9"
            description={`
            <p>
            人狼プレイヤーを一人でも吊ることができたら村人陣営の勝利です。<br />
            人狼プレイヤーが一人もいない平和村の場合は誰も吊らない(全員の得票数が1票以下になる)ことが勝利条件となります。<br />
            (吊人プレイヤーが勝利した場合は強制で敗北となります)
            </p>
            `}
            roles={[
              {
                imgSrc: characters.murabito.imgUrl,
                altText: characters.murabito.name,
                caption: characters.murabito.name,
                description: `
                平和な村に住む無垢でかわいい村人さん。特殊な能力はありません。
                他プレイヤーの言葉だけをヒントに、誰が人狼か推理しましょう。
                `,
              },
              {
                imgSrc: characters.uranaishi.imgUrl,
                altText: characters.uranaishi.name,
                caption: characters.uranaishi.name,
                description: `
                ミステリアスでクールな雰囲気の占い師さん。
                夜の間に、他プレイヤーまたは場のカードを選択します。他プレイヤーのカードを選択した場合、そのプレイヤーの役職を確認できます。
                場のカードを選択した場合、場にあるカード2枚の役職を確認できます。この行動は怪盗が役職を取り替える前に行われます。
                `,
              },
              {
                imgSrc: characters.kaito.imgUrl,
                altText: characters.kaito.name,
                caption: characters.kaito.name,
                description: `
                狙った獲物のハートを盗む怪盗さん。夜の間に、他プレイヤーのカードを選択すると「自分の役職」と「選択したプレイヤーの役職」を
                入れ替えることができます。（入れ替えなくても良い）カードの交換は各自の役職確認後に行われるため、交換されたプレイヤーは
                交換されたことを知らないままゲームがスタートします。勝利条件は交換後の役職に準じます。
                `,
              },
            ]}
          />

          <RoleExplanation
            roleTitle="人狼陣営"
            backgroundColor="#ecd7b7"
            description={`
            <p>
            人狼プレイヤーが誰も吊られなければ人狼陣営の勝利です。<br />
            (吊人プレイヤーが勝利した場合は強制で敗北となります)
            </p>`}
            roles={[
              {
                imgSrc: characters.jinroh.imgUrl,
                altText: characters.jinroh.name,
                caption: characters.jinroh.name,
                description:
                  'イタズラ好きなかわいい人狼さん。夜の間に他の人狼プレイヤーを確認することができます。',
              },
              {
                imgSrc: characters.kyojin.imgUrl,
                altText: characters.kyojin.name,
                caption: characters.kyojin.name,
                description: `
                人狼さんにひそかな思いを寄せる狂人さん。
                特殊な能力はありませんが、村人とは陣営が異なり、人狼陣営に所属します。
                狂人が吊られても人狼陣営の敗北にはなりません。
                プレイヤーに人狼がいない平和村の場合、勝利条件は「人間SIDE」となります(村人と同じ扱いとなります)。
                `,
              },
            ]}
          />

          <RoleExplanation
            roleTitle="吊人陣営"
            backgroundColor="#b7e2ec"
            description={`
            <p>
            吊人プレイヤーが吊られた場合、吊られたプレイヤーの勝利となり、他の陣営はすべて敗北となります。
              </p>
            `}
            roles={[
              {
                imgSrc: characters.tsuribito.imgUrl,
                altText: characters.tsuribito.name,
                caption: characters.tsuribito.name,
                description: `
                丈夫な縄を自分の首にかけてぶら下がる不思議な遊びが大好きな吊人さん。
                吊られるのが目的です。吊られると一人勝ちとなります。
                `,
              },
            ]}
          />
        </div>
      </section>

      <p>ver 1.0.1</p>
    </DefaultLayout>
  );
};
