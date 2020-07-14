import { NgModule, APP_INITIALIZER } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { OAuthModule } from 'angular-oauth2-oidc';

import { AuthService } from './services/auth/auth.service';
import { ModelsService } from './services/models/models.service';
import { AuthorizationInterceptor } from './interceptors/authorization/authorization.interceptor';
import { BaseUrlInterceptor } from './interceptors/base-url/base-url.interceptor';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    OAuthModule.forRoot()
  ],
  providers: [
    AuthService,
    {
      provide: APP_INITIALIZER,
      useFactory: (service: AuthService) => () => service.start(),
      deps: [AuthService],
      multi: true
    },
    ModelsService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthorizationInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: BaseUrlInterceptor, multi: true }
  ]
})
export class CoreModule { }
