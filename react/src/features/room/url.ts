import { ExhaustiveError } from "@/error";
import { JINROH_API_BASE_URL } from "@/url";

type BaseUrl = typeof JINROH_API_BASE_URL;

type BackendApiPaths = {
  getRoomIndex: '/room-index';
  getGameStart: '/game-start';
  getRoomFinish: '/room-finish';
}

const backendApiPaths: BackendApiPaths = {
  getRoomIndex: '/room-index',
  getGameStart: '/game-start',
  getRoomFinish: '/room-finish',
}

type BackendApiPathName = keyof BackendApiPaths;
type BackendApiPath = (typeof backendApiPaths)[keyof typeof backendApiPaths];

export const getBackendApiPath = (path: BackendApiPathName): `${BaseUrl}${BackendApiPath}` => {
  switch (path) {
    case 'getRoomIndex':
    case 'getGameStart':
    case 'getRoomFinish':
      {
        return `${JINROH_API_BASE_URL}${backendApiPaths[path]}`;
      }
    default:
      throw new ExhaustiveError(path);
  }
}

