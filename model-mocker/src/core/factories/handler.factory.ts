import { Request } from 'express';

import { random } from '../../utils';

import { ModelRoute, ModelResponse, ObjectField } from '../models';
import { SchemaFactory } from '.';

const randomOfType = (type: string) => {
  return new Map<string, Function>([
    ['string', random.string],
    ['number', random.number],
    ['date', random.date]
  ])
    .get(type)?.();
};

const build = (route: ModelRoute) => {

  const successResponse = route.response.find((r: ModelResponse) => r.status >= 200 && r.status <= 299);

  return async ({}, request: Request) => {

    if (route.body) {
      SchemaFactory.build(route.body).validate(request.body);
    }

    if (!successResponse) {
      return Promise.resolve({});
    }

    return Promise.resolve(
      successResponse.body
        .reduce((obj: Object, f: ObjectField) => ({ ...obj, [f.name]: randomOfType(f.type) }), {})
    );
  };
};

export const HandlerFactory = {
  build
};
