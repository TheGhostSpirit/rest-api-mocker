import { Route } from '../../utils';

import modelsController from './models.controller';

/**
 * The models routes to mount on /models.
 */
const routes: Route[] = [
  {
    method: 'get',
    path: '/',
    handler: modelsController.list
  },
  {
    method: 'get',
    path: '/:id',
    handler: modelsController.findOne
  },
  {
    method: 'post',
    path: '/',
    handler: modelsController.create,
  },
  {
    method: 'put',
    path: '/:id',
    handler: modelsController.update,
    options: {
      update: true
    }
  },
  {
    method: 'delete',
    path: '/:id',
    handler: modelsController.remove,
    options: {
      update: true
    }
  }
];

export default routes;
