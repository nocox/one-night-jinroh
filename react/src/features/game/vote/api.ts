import { isVoteIndexRequestBody } from './type';
import type {
  FetchVoteIndex,
  VoteIndexRequestBody,
  PostVoteForm,
} from './type';
import { InvalidResponseBodyError, UnexpectedError } from '@/features/error';
import { JINROH_API_BASE_URL } from '@/url';

export const fetchVoteIndex: FetchVoteIndex = async () => {
  const res = await fetch(JINROH_API_BASE_URL + '/vote-index', {
    method: 'GET',
    credentials: 'include',
  });

  if (!res.ok) {
    throw new UnexpectedError(`
      fetchVoteIndex failed: ${res.status} ${res.statusText}
    `);
  }

  const voteIndex = (await res.json()) as VoteIndexRequestBody;

  if (!isVoteIndexRequestBody(voteIndex)) {
    throw new InvalidResponseBodyError(`
      fetchVoteIndex failed: ${JSON.stringify(voteIndex)}
    `);
  }

  return voteIndex;
};

export const postVoteForm: PostVoteForm = async (dto) => {
  const { gameParticipantId } = dto;

  const res = await fetch(JINROH_API_BASE_URL + '/vote', {
    method: 'POST',
    credentials: 'include',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ gameParticipantId }),
  });

  if (!res.ok) {
    throw new UnexpectedError(`
      postVoteForm failed: ${res.status} ${res.statusText}
    `);
  }
};
