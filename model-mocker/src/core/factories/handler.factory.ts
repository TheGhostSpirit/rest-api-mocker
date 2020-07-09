import { Request } from 'express';

import { ModelRoute, ModelResponse } from '../models';
import { SchemaFactory } from '.';
import { ResponseFactory } from './response.factory';

const build = (route: ModelRoute) => {

  const successResponse = route.response.find((r: ModelResponse) => r.status >= 200 && r.status <= 299);

  return async ({}, request: Request) => {

    if (route.body) {
      SchemaFactory.build(route.body).validate(request.body);
    }

    if (route.params) {
      SchemaFactory.build(route.params).validate(request.params);
    }

    if (route.query) {
      SchemaFactory.build(route.query).validate(request.query);
    }

    if (!successResponse) {
      return Promise.resolve({});
    }

    return Promise.resolve(
      ResponseFactory.build(successResponse.body)
    );
  };
};

export const HandlerFactory = {
  build
};
