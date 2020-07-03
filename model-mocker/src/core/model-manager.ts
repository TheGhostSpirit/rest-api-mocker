import fs from 'fs';

import { logger } from '../utils';
import { Model } from './model';

const fetch =  async (path: string): Promise<Buffer> => {
  return new Promise((resolve, reject) => {
    fs.readFile(path, (err, data) => {
      if (err) {
        logger.error(err);
        reject();
      }
      resolve(data);
    });
  });
};

const toModel = (buffer: Buffer): Model => {
  return JSON.parse(buffer.toString()) as Model;
};

const getLatest = async (path: string): Promise<Model> => {
  const buffer = await fetch(path);
  return toModel(buffer);
};

export default {
  fetch,
  toModel,
  getLatest
};
