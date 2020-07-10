import { buildRoutes, Path } from '../utils';

import modelsRoutes from './models/models.routes';
import convertRoutes from './convert/convert.routes';

/**
 * The routes to mount on /api.
 */
const paths: Path[] = [
  { path: '/models', routes: modelsRoutes },
  { path: '/convert', routes: convertRoutes }
];

const router = buildRoutes(paths);

export default router;
