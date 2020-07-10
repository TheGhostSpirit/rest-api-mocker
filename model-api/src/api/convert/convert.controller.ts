import { Request } from 'express';
import createHttpError from 'http-errors';

import convertService from './convert.service';

const toJson = async ({}, request: Request) => {
  try {
    return convertService.toJson(request.body);
  } catch(error) {
    throw createHttpError(400, error.message);
  }
};

const toYaml = async ({}, request: Request) => {
  try {
    return convertService.toYaml(request.body);
  } catch(error) {
    throw createHttpError(400, error.message);
  }
};

export default { toJson, toYaml };
