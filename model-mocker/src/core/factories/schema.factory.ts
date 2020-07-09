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

const transformFields = (fields: ObjectField[]) => {
  const schema: yup.Schema<any> = yup.object().shape<any>(
    fields
      .map(field => [field,
        field.properties
          ? transformFields(field.properties)
          : field.items
            ? yup.array().of(transformFields(field.items))
            : getSchemaOfType(field.type)
      ])
      .map(([field, schema]) => [field, field.required ? schema.required() : schema])
      .map(([field, schema]) => ({ [field.name]: schema }))
      .reduce((pv, cv) => ({ ...pv, ...cv }), {})
  );
  return schema;
};

const build = (fields: ObjectField[]) => {
  return new Schema(transformFields(fields));
};

export const SchemaFactory = {
  build
};
