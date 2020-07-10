import * as yup from 'yup';

export class Schema<T> {

  schema: yup.Schema<T>;

  constructor(schema: yup.Schema<T>) {
    this.schema = schema;
  }

  validate(instance: T): T {
    return this.schema.validateSync(instance, { stripUnknown: true });
  }

}
