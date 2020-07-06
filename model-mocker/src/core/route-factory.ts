import { Router, Request, Response } from 'express';
import path from 'path';

import { logger } from '../utils';
import { CONFIG } from '../config';

import { Model } from './model';
import { Route, HttpMethod } from './route';
import { connectDatabase } from './http/middlewares';
import handlerFactory from './handler-factory';

type ModelRoute = Model['api']['routes'][0];

const modelToRoutes = (model: Model): Route[] => {
  return model.api.routes
    .map((r: ModelRoute) => ({
      path: r.path,
      method: r.method as HttpMethod,
      handler: handlerFactory.makeHandler(r)
    }));
};

const buildRouter = (routes: Route[]): Router => {
  const router = new (Router as any)();

  routes.forEach(r => {
    logger.info(`${r.method.toUpperCase()} ${path.join(`http://127.0.0.1:${CONFIG.port}`, r.path)}`);
    router[r.method](
      path.join('/', r.path),
      connectDatabase(r.handler),
      (request: Request, response: Response) => response.json(request.app.locals.result)
    );
  });

  return router;
};

export default {
  modelToRoutes,
  buildRouter
};
