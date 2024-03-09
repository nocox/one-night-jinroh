import { css } from 'styled-system/css';

import { JoinRoom } from './components/JoinRoom';
import { MakeRoomButton } from './components/MakeRoomButton';
import { NewsField } from './components/NewsField';
import { RoleExplanation } from './components/RoleExplanation';
import { TitleCharacters } from './components/TitleCharacters';
import { TitleLogo } from './components/TitleLogo';
import { DefaultLayout } from '@/components';

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
        <JoinRoom className={styles.roomButton} />
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

          <RoleExplanation roleType="murabito" />

          <RoleExplanation roleType="jinroh" />

          <RoleExplanation roleType="turibito" />
        </div>
      </section>

      <p>ver 1.0.1</p>
    </DefaultLayout>
  );
};
