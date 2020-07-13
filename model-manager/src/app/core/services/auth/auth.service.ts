import { Injectable } from '@angular/core';
import { OAuthService, AuthConfig } from 'angular-oauth2-oidc';

import CONFIG from '../../../config/config';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private oauthService: OAuthService) {
    this.oauthService.configure({
      issuer: CONFIG.auth.issuer,
      redirectUri: window.location.origin,
      postLogoutRedirectUri: window.location.origin,
      clientId: CONFIG.auth.clientId,
      scope: CONFIG.auth.scope,
      skipIssuerCheck: true,
      strictDiscoveryDocumentValidation: false
    });
    this.oauthService.loadDiscoveryDocumentAndTryLogin();
  }

  login(): void {
    this.oauthService.initLoginFlow();
  }

  logout(): void {
    this.oauthService.logOut();
  }

  get name(): string {
    let claims = this.oauthService.getIdentityClaims() as any;
    return claims?.name;
  }

  get isLoggedIn(): boolean {
    return this.oauthService.getIdentityClaims() !== null;
  }

}
