import { Server } from 'http';
import fs from 'fs';

import { CONFIG } from './config';

import modelManager from './core/model-manager';
import routeFactory from './core/route-factory';
import { Model } from './core/model';
import { getServer } from './core/http';

const main = async () => {

  let model: Model;
  let server: Server;

  fs.watch(CONFIG.model.repository, {}, async (_, fileName) => {
    if (fileName == CONFIG.model.name) {
      model = await  modelManager.getLatest(CONFIG.model.fullPath);
      const routes = routeFactory.modelToRoutes(model);
      server
        ? server.close(() => server = getServer(routeFactory.buildRouter(routes)))
        : server = getServer(routeFactory.buildRouter(routes));
    }
  });

};

main();
