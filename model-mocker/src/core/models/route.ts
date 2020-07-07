/**
 * Represents the possible values for the API's endpoints HTTP methods.
 */
export type HttpMethod = 'get' | 'post' | 'put' | 'delete' | 'patch';

export interface Route {
  method: HttpMethod;
  path: string;
  handler: (...args: any) => Promise<any>;
}
