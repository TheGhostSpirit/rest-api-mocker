import * as yup from 'yup';

import { ObjectField, Schema } from '../models';

const getSchemaOfType = (type: string) => {
  return new Map<string, any>([
    ['string', yup.string()],
    ['number', yup.number()],
    ['date', yup.date()]
  ])
    .get(type);
};

const build = (fields: ObjectField[]): Schema<any> => {
  const schema = yup.object().shape<any>(
    fields
      .map(field => [field, getSchemaOfType(field.type)])
      .map(([field, schema]) => [field, field.required ? schema.required() : schema])
      .map(([field, schema]) => ({ [field.name]: schema }))
      .reduce((pv, cv) => ({ ...pv, ...cv }), {})
  );
  return new Schema(schema);
};

export const SchemaFactory = {
  build
};
