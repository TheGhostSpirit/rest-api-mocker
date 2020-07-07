import express from 'express';
import cors from 'cors';

import { CONFIG } from '../../config';
import { logger } from '../../utils';
import { handleError, notFound } from './middlewares';

const getServer = (routes: express.Router) => express()
  .use(cors())
  .use(express.json())
  .use(express.urlencoded({ extended: false }))
  .use('/', routes)
  .use(notFound)
  .use(handleError)
  .listen(CONFIG.port, () => logger.info(`Server up and running on port ${CONFIG.port}!`))
  .on('error', error => (logger.error(error), process.exit(1)));


export const Http = {
  getServer
};
