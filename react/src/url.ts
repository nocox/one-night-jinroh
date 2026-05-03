const isDevelopment = process.env.NODE_ENV === 'development';

export const JINROH_API_BASE_URL = isDevelopment ? 'http://localhost:8080' : '';
