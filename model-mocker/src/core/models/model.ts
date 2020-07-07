import { Model, ObjectField } from 'model-share/types/model';

export { Model, ObjectField };
export type ModelRoute = Model['api']['routes'][0];
export type ModelResponse = ModelRoute['response'][0];
