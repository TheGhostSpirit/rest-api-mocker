import { Request, NextFunction } from 'express';

import { Database } from '../../database';

/**
 * Mongo Database connection middleware.
 * @param callback the callback to execute once the connection to the database is established.
 * @returns the connection middleware function.
 */
export const connectDatabase = (callback: (...args: any) => Promise<any>) => async (
  request: Request, {}, next: NextFunction
) => {
  try {
    if (!request.app.locals.db) {
      request.app.locals.db = await Database.connect();
    }
    const result = await callback(request.app.locals.db, request);
    request.app.locals.result = result;
    next();
  } catch (error) {
    next(error);
  }
};
