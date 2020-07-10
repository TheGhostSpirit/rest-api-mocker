import { Request } from 'express';
import createHttpError from 'http-errors';

import { ModelRoute, ModelResponse } from '../models';
import { SchemaFactory, ResponseFactory } from '.';

const build = (route: ModelRoute) => {

  const successResponse = route.response.find((r: ModelResponse) => r.status >= 200 && r.status <= 299);
  const validationErrorResponse = route.response.find((r: ModelResponse) => r.status === 400 || r.status === 422);

  return async ({}, request: Request) => {

    try {
      if (route.body) {
        SchemaFactory.build(route.body).validate(request.body);
      }

      if (route.params) {
        SchemaFactory.build(route.params).validate(request.params);
      }

      if (route.query) {
        SchemaFactory.build(route.query).validate(request.query);
      }
    } catch (error) {

      if (!validationErrorResponse) {
        throw createHttpError(400, error);
      }

      throw createHttpError(
        validationErrorResponse.status,
        ResponseFactory.build(validationErrorResponse.body)
      );
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
