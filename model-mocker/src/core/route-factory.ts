import { Router, Request, Response } from 'express';
import path from 'path';

import { logger } from '../utils';
import { CONFIG } from '../config';

import { Model } from './model';
import { Route, HttpMethod } from './route';
import { connectDatabase } from './http/middlewares';

const modelToRoutes = (model: Model): Route[] => {
  return model.api.routes
    .map((r: Model['api']['routes'][0]) => ({
      path: r.path,
      method: r.method as HttpMethod,
      handler: () => Promise.resolve({ name: 'tototiti' })
    }));
};

const buildRouter = (routes: Route[]): Router => {
  const router = new (Router as any)();

  routes.forEach(r => {
    logger.info(`${r.method.toUpperCase()} ${path.join(`http://127.0.0.1:${CONFIG.port}`, r.path)}`);
    router[r.method](
      r.path,
      connectDatabase(r.handler),
      (request: Request, response: Response) => response.json(request.app.locals.result)
    );
  });

  return router;
}

export default {
  modelToRoutes,
  buildRouter
};
