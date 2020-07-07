import * as yup from 'yup';
import createHttpError from 'http-errors';

export class Schema<T> {

  schema: yup.Schema<T>;

  constructor(schema: yup.Schema<T>) {
    this.schema = schema;
  }

  validate(instance: T): T {
    try {
      return this.schema.validateSync(instance, { stripUnknown: true });
    } catch (error) {
      throw createHttpError(422, error.message);
    }
  }

}
