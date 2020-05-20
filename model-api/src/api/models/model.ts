import { ObjectId } from 'mongodb';

/**
 * Represents a REST API Model.
 */
export interface Model {
  _id?: ObjectId;
  version: number;
  api: {
    name: string;
    description: string;
    contact: {
      name: string;
      email: string;
    }
    license: string;
    version: number;
    routes: {
      path: string;
      method: string;
      query: ObjectField[];
      params: ObjectField[];
      body: ObjectField[];
      response: {
        status: number;
        body: ObjectField[];
      }[];
    }[];
  }
}

interface ObjectField {
  name: string;
  type: string;
  required: boolean;
}
