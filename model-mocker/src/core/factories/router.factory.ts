import { Router, Request, Response } from 'express';
import path from 'path';

import { logger } from '../../utils';
import { CONFIG } from '../../config';

import { Model, ModelRoute, Route, HttpMethod } from '../models';
import { connectDatabase } from '../http/middlewares';
import { HandlerFactory } from '.';

const modelToRoutes = (model: Model): Route[] => {
  return model.api.routes
    .map((r: ModelRoute) => ({
      path: r.path,
      method: r.method as HttpMethod,
      handler: HandlerFactory.build(r)
    }));
};

const build = (model: Model): Router => {
  const routes = modelToRoutes(model);
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

export const RouterFactory = {
  build
};
