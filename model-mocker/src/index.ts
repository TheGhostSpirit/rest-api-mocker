import { Server } from 'http';
import chokidar from 'chokidar';

import { CONFIG } from './config';

import { Http } from './core/http';
import { Model } from './core/models';
import { ModelFactory, RouterFactory } from './core/factories';

const main = async () => {

  let model: Model;
  let server: Server;

  chokidar.watch(CONFIG.model.repository).on('all', async ({}, path) => {
    if (path == CONFIG.model.fullPath) {
      model = await ModelFactory.build(CONFIG.model.fullPath);
      server
        ? server.close(() => server = Http.getServer(RouterFactory.build(model)))
        : server = Http.getServer(RouterFactory.build(model));
    }
  });

};

main();
