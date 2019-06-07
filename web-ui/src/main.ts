import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';
import { KeycloakService } from './app/core';

if (environment.production) {
  enableProdMode();
}

KeycloakService.init()
  .then(() => platformBrowserDynamic().bootstrapModule(AppModule))
  .catch(e => {
    console.log(e);
    window.location.reload();
  });
