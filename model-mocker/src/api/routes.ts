import { buildRoutes, Path } from '../utils';

/**
 * The routes to mount on /api.
 */
const paths: Path[] = [];

const router = buildRoutes(paths);

export default router;
