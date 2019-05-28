export const environment = {
  production: false,
  api_url: 'https://localhost',
  images_url: 'https://localhost/api/v1/image/',
  search_url: 'https://localhost/api/v1/search/ad?q=',
  category_url: 'https://localhost/api/v1/category/',
  ad_url: 'https://localhost/api/v1/ad',
  user_url: 'https://localhost/api/v1/user',
  keycloak: {
    url: 'https://localhost/auth',
    realm: 'apigw',
    clientId: 'web-sso',
    checkLoginIframe: true,
    onLoad: 'login-required',
    responseMode: 'fragment',
  },
};
