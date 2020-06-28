import express from 'express';
import cors from 'cors';
import fs from 'fs';

import { logger } from './utils';
import { CONFIG } from './config';
import { handleError, notFound } from './middlewares';
import routes from './api/routes';

const getModel = async () => {
  return new Promise((resolve, reject) => {
    fs.readFile('/tmp/model.yml', (err, data) => {
      if (err) {
        logger.error(err);
        reject();
        process.exit(1);
      }
      resolve(data);
    });
  });
};

const getServer = () => express()
  .use(cors())
  .use(express.json())
  .use(express.urlencoded({ extended: false }))
  .use('/api', routes)
  .use(notFound)
  .use(handleError)
  .listen(CONFIG.port, () => logger.info(`Server up and running on port ${CONFIG.port}!`))
  .on('error', error => (logger.error(error), process.exit(1)));

/**
 * Express HTTP application entrypoint.
 * Routes and middlewares setup.
 */
const main = async () => {

  let model = await getModel();

  let server = getServer();

  fs.watch('/tmp', {}, async (_, fileName) => {
    if (fileName == 'model.yml') {
      model = await getModel();
      server.close(() => server = getServer());
    }
  });

};

main();
