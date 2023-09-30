import { useEffect, useState } from 'react';
import { UranaishiFormContent } from './UranaishiFormContent';
import { UranaishiFormResult } from './UranaishiFormResult';
import { ExhaustiveError, UnexpectedError } from '@/error';
import {
  fetchNightUranaishiAction,
  postNightUranaishiAction,
} from '@/features/game/night/api';
import type {
  NightUranaiResult,
  UranaiStatus,
} from '@/features/game/night/type';
import type { OtherPlayer, Role } from '@/features/game/type';

const generateActLog = (nightUranaiActionResult: NightUranaiResult) => {
  const { status, participantId, roles, user } = nightUranaiActionResult;

  switch (status) {
    case 'NOT_CHOOSE': {
      return '占いを実行しませんでした';
    }
    case 'HOLIDAY_ROLES': {
      const holidayRoles = roles.map((role) => role.roleName).join('と');

      return `おやすみ中のカードは${holidayRoles}でした`;
    }
    case 'PLAYER': {
      if (user === null || participantId === null) {
        throw new UnexpectedError(`
          予期せぬエラーが発生しました。userまたはparticipantIdがnullです。
          ${JSON.stringify(nightUranaiActionResult)} 
        `);
      }

      return `${user.userName}さんは${roles[0].roleName}でした`;
    }
    default: {
      throw new ExhaustiveError(status);
    }
  }
};

type Props = {
  otherPlayerList: OtherPlayer[];
};

export const UranaishiForm: React.FC<Props> = ({ otherPlayerList }) => {
  const [actLog, setActLog] = useState<string | undefined>();
  const [roles, setRoles] = useState<Role[] | undefined>();
  const [selectedPlayerId, setSelectedPlayerId] = useState<number | undefined>(
    undefined,
  );
  const [uranaiStatus, setUranaiStatus] =
    useState<UranaiStatus>('HOLIDAY_ROLES');

  useEffect(() => {
    const fetchNightUranaishiActionResultAsync = async () => {
      // TODO: 誰も占わなずに行動を完了したとき、ページリロードするとバックエンドで500エラーが起きているため修正が必要
      const nightUranaiActionResult = await fetchNightUranaishiAction();

      if (nightUranaiActionResult === undefined) {
        return;
      }

      setActLog(generateActLog(nightUranaiActionResult));
      setRoles(nightUranaiActionResult.roles);
    };

    void fetchNightUranaishiActionResultAsync();
  }, []);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.value === 'HOLIDAY_ROLES') {
      setSelectedPlayerId(undefined);
      setUranaiStatus('HOLIDAY_ROLES');
    } else {
      setSelectedPlayerId(Number(e.target.value));
      setUranaiStatus('PLAYER');
    }
  };

  const handleSubmit = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();

    const dto = { participantId: selectedPlayerId, status: uranaiStatus };
    const nightUranaiActionResult = await postNightUranaishiAction(dto);
    setActLog(generateActLog(nightUranaiActionResult));
    setRoles(nightUranaiActionResult.roles);
  };

  return (
    <>
      {actLog === undefined && roles === undefined && (
        <UranaishiFormContent
          otherPlayerList={otherPlayerList}
          handleChange={handleChange}
          handleSubmit={handleSubmit}
          selectedPlayerId={selectedPlayerId}
          uranaiStatus={uranaiStatus}
        />
      )}

      {actLog !== undefined && roles !== undefined && (
        <UranaishiFormResult actLog={actLog} roles={roles} />
      )}
    </>
  );
};
