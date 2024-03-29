import { resolve } from 'path'
import { defineConfig } from 'vite'
import tsconfigPaths from 'vite-tsconfig-paths'
import react from '@vitejs/plugin-react-swc'

// https://vitejs.dev/config/
export default defineConfig({
  build: {
    rollupOptions: {
      input: {
        main: resolve(__dirname, 'index.html'),
        top_page: "src/features/top/TopPage.index.tsx",
        room_page: "src/features/room/RoomPage.index.tsx",
        night_page: "src/features/game/night/NightPage.index.tsx",
        talk_page: "src/features/game/talk/TalkPage.index.tsx",
        vote_page: "src/features/game/vote/VotePage.index.tsx",
        tally_page: "src/features/game/tally/TallyPage.index.tsx",
        result_page: "src/features/game/result/ResultPage.index.tsx",
      },
      output: {
        dir: "../spring/src/main/resources/static/",
        assetFileNames: (assetInfo) => {
          let extType = assetInfo.name.split('.')[1];
          //Webフォントファイルの振り分け
          if (/ttf|otf|eot|woff|woff2/i.test(extType)) {
            extType = 'fonts';
          }
          if (/png|jpe?g|svg|gif|tiff|bmp|ico/i.test(extType)) {
            extType = 'images';
          }
          //ビルド時のCSS名を明記してコントロールする
          if(extType === 'css') {
            return `assets/css/style.css`;
          }

          if(extType === 'tsx') {
            return `assets/js/[name].build.js`;
          }

          return `assets/${extType}/[name][extname]`;
        },
        chunkFileNames: 'assets/js/[name].build.js',
        entryFileNames: 'assets/js/[name].build.js',
      },
    },
  },
  plugins: [react(),tsconfigPaths()],
})
