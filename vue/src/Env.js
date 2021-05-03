const isDevelopment = process.env.NODE_ENV === 'development'

export const JINROH_API_BASE_URL = isDevelopment ? 'http://localhost:8080' : process.env.VUE_APP_API_BASE_URL
console.log('JINROH_API_BASE_URL : ', JINROH_API_BASE_URL);