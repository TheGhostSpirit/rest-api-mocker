import { Server } from 'http';
import fs from 'fs';

import { CONFIG } from './config';

import { Http } from './core/http';
import { Model } from './core/models';
import { ModelFactory, RouterFactory } from './core/factories';

const main = async () => {

  let model: Model;
  let server: Server;

  fs.watch(CONFIG.model.repository, {}, async (_, fileName) => {
    if (fileName == CONFIG.model.name) {
      model = await ModelFactory.build(CONFIG.model.fullPath);
      server
        ? server.close(() => server = Http.getServer(RouterFactory.build(model)))
        : server = Http.getServer(RouterFactory.build(model));
    }
  });

};

main();
