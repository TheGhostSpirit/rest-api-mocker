import { Route } from '../../utils';

import convertController from './convert.controller';

/**
 * The models routes to mount on /models.
 */
const routes: Route[] = [
  {
    method: 'post',
    path: '/yaml',
    handler: convertController.toYaml
  },
  {
    method: 'post',
    path: '/json',
    handler: convertController.toJson
  }
];

export default routes;
