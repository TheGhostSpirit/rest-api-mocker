import * as yup from 'yup';
import createHttpError from 'http-errors';

export const validateSchema = <T> (schema: yup.Schema<T>, instance: T): T => {
  try {
    return schema.validateSync(instance, { stripUnknown: true });
  } catch(error) {
    throw createHttpError(422, error.message);
  }
};
