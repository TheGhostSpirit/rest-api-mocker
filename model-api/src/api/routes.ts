import { buildRoutes, Path } from '../utils';

import modelsRoutes from './models/models.routes';

/**
 * The routes to mount on /api.
 */
const paths: Path[] = [
  { path: '/models', routes: modelsRoutes }
];

const router = buildRoutes(paths);

export default router;
