export const environment = {
  production: true,
  api_url: 'https://localhost/api/v1',
  keycloak: {
    url: 'https://localhost/auth',
    realm: 'apigw',
    clientId: 'web-sso',
    checkLoginIframe: true,
    onLoad: 'login-required',
    responseMode: 'fragment',
  }
};
