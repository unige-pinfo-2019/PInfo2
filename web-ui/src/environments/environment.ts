export const environment = {
  production: false,
  api_url: 'http://pinfo2.unige.ch:',
  images_url: 'http://pinfo2.unige.ch:14080/image/',
  category_url: 'http://pinfo2.unige.ch:12080/category/',
  ad_url: 'http://pinfo2.unige.ch:15080/ad',
  user_url: 'http://pinfo2.unige.ch/user',
  keycloak: {
    url: 'https://localhost/auth',
    realm: 'apigw',
    clientId: 'web-sso',
    checkLoginIframe: true,
    onLoad: 'login-required',
    responseMode: 'fragment',
  },
};
