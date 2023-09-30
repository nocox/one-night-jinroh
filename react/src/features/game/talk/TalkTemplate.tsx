import type { TalkIndexResponseBody } from './type';

type Props = {
  talkIndex: TalkIndexResponseBody;
};
export const TalkTemplate: React.FC<Props> = ({ talkIndex }) => {
  console.log(talkIndex);

  return (
    <div>
      <h1>TalkTemplate</h1>
    </div>
  );
};
