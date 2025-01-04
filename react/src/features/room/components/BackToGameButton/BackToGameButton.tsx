import type React from "react";
import {css} from "../../../../../styled-system/css";

const styles = {
  joinedButtonWrapper: css({
    display: "flex",
    justifyContent: "center"
  }),
}


export const BackToGameButton: React.FC  = () => {
  return (
    <div className={styles.joinedButtonWrapper}>
      <button
          onClick={() => {
            location.href = '/night';
          }}
      >
        参加中のゲームに戻る
      </button>
    </div>
  );
};
