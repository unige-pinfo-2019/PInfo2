export const environment = {
  production: false,
  api_url: 'https://pinfo2.unige.ch',
  images_url: 'https://pinfo2.unige.ch/api/v1/image/',
  search_url: 'https://pinfo2.unige.ch/api/v1/search/ad?q=',
  category_url: 'https://pinfo2.unige.ch/api/v1/category/',
  ad_url: 'https://pinfo2.unige.ch/api/v1/ad',
  user_url: 'https://pinfo2.unige.ch/api/v1/user',
  keycloak: {
    url: 'https://pinfo2.unige.ch/auth',
    realm: 'apigw',
    clientId: 'web-sso',
    checkLoginIframe: true,
    onLoad: 'login-required',
    responseMode: 'fragment',
  },
};
