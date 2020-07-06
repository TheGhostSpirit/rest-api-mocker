import * as yup from 'yup';

import { ObjectField } from './model';

const getSchemaOfType = (type: string) => {
  return new Map<string, any>([
    ['string', yup.string().required()],
    ['number', yup.number().required()],
    ['date', yup.date().required()]
  ])
    .get(type);
};

const buildSchema = (fields: ObjectField[]): yup.Schema<any> => {
  return yup.object().shape<any>(
    fields
      .map(field => ({ [field.name]: getSchemaOfType(field.type) }))
      .reduce((pv, cv) => ({ ...pv, ...cv }), {})
  );
};

export const schemaFactory = {
  buildSchema
};
