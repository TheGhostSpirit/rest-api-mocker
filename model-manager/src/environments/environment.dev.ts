export const environment = {
  production: false,
  api: {
    url: 'http://localhost:3000/api'
  },
  mocker: {
    url: 'http://localhost:3001'
  },
  auth: {
    tenantId: 'eae410ed-7a7e-451a-b931-636bbc191a74',
    clientId: '7f678163-f056-41c8-ba95-5a7a42625beb',
    scope: 'openid profile email api://model/all',
    get issuer(): string {
      return `https://sts.windows.net/${this.tenantId}/v2.0`;
    }
  }
};
