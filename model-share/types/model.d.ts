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
      query: QueryField[];
      params: ParamField[];
      body: BodyField[];
      response: {
        status: number;
        body: ResponseBodyField[];
      }[];
    }[];
  }
}

export interface QueryField extends ObjectField {
  name: string;
  type: string;
  required?: boolean;
}

export interface ParamField extends ObjectField {
  name: string;
  type: string;
}

export interface BodyField extends ObjectField {
  name: string;
  type: string;
  required?: boolean;
  properties?: BodyField[];
  items?: BodyField[];
}

export interface ResponseBodyField extends ObjectField {
  name: string;
  type: string;
  value?: any;
  properties?: ResponseBodyField[];
  items?: ResponseBodyField[];
}

/**
 * Represents an object field (used internally by models).
 */
export interface ObjectField {
  name: string;
  type: string;
  value?: any;
  required?: boolean;
  properties?: ObjectField[];
  items?: ObjectField[];
}
