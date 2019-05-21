export const environment = {
  production: false,
  apiUrl: 'URL_DE_API',
  images_url: 'http://localhost:14080/image/',
  keycloak: {
    url: 'https://localhost/auth',
    realm: 'apigw',
    clientId: 'web-sso',
    checkLoginIframe: true,
    onLoad: 'login-required',
    responseMode: 'fragment',
  },
};
