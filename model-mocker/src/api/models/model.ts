/**
 * Represents a REST API Model.
 */
export interface Model {
  owner?: string;
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

/**
 * Represents an object field (used internally bu models).
 */
export interface ObjectField {
  name: string;
  type: string;
  required: boolean;
}
