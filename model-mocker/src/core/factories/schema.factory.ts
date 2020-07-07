import * as yup from 'yup';

import { ObjectField, Schema } from '../models';

const getSchemaOfType = (type: string) => {
  return new Map<string, any>([
    ['string', yup.string().required()],
    ['number', yup.number().required()],
    ['date', yup.date().required()]
  ])
    .get(type);
};

const build = (fields: ObjectField[]): Schema<any> => {
  const schema = yup.object().shape<any>(
    fields
      .map(field => ({ [field.name]: getSchemaOfType(field.type) }))
      .reduce((pv, cv) => ({ ...pv, ...cv }), {})
  );
  return new Schema(schema);
};

export const SchemaFactory = {
  build
};
