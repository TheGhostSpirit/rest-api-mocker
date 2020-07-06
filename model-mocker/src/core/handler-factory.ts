import { Request } from 'express';

import { Model, ObjectField } from './model';
import { random } from '../utils';
import { schemaFactory } from './schema-factory';
import { validateSchema } from './validator';

type ModelRoute = Model['api']['routes'][0];
type ModelResponse = ModelRoute['response'][0];

const randomOfType = (type: string) => {
  return new Map<string, Function>([
    ['string', random.string],
    ['number', random.number],
    ['date', random.date]
  ])
    .get(type)?.();
};

// const getHandler = (route: ModelRoute) => {
//   return route.path.split('/').reverse().find((r: string) => !r.startsWith(':'));
// };

const makeHandler = (route: ModelRoute) => {

  const successResponse = route.response.find((r: ModelResponse) => r.status >= 200 && r.status <= 299);

  return async ({}, request: Request) => {

    if (route.body) {
      console.log(validateSchema(schemaFactory.buildSchema(route.body), request.body));
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

export default {
  makeHandler
};
