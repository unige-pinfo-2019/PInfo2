export const environment = {
  production: false,
  api_url: 'http://pinfo2.unige.ch:',
  images_url: '${this.api_url}:14080/image/',
  category_url: '${this.api_url}:12080/category/',
  ad_url: '${this.api_url}:15080/ad',
  user_url: '${this.api_url}/user',
  keycloak: {
    url: 'https://localhost/auth',
    realm: 'apigw',
    clientId: 'web-sso',
    checkLoginIframe: true,
    onLoad: 'login-required',
    responseMode: 'fragment',
  },
};
