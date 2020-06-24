import { Db, ObjectId } from 'mongodb';
import { Request } from 'express';
import createHttpError from 'http-errors';

import { validateSchema, validateRouteParams, Repository, useRepository } from '../../utils';

import { Model } from './model';
import modelService from './models.service';
import modelSchema from './models.schema';

/**
 * Returns the repository associated with the model collection.
 * @returns the model repository.
 */
const modelRepository = (database: Db): Repository<Model> => useRepository(database, 'models');

/**
 * Retrieves the list of models from the database.
 * @param database the database in which to look for models.
 * @returns a list of models.
 */
const list = async (database: Db, request: Request): Promise<Model[]> => {
  return modelService.list(modelRepository(database), (request.user as any).email);
};

/**
 * Retrieves one model from the database.
 * @param database the database in which to find the model.
 * @param request the Express request object.
 * @returns one model matching the given id.
 * @throws a 404 http error if the id is invalid.
 */
const findOne = async (database: Db, request: Request): Promise<Model> => {
  const [ id ] = validateRouteParams(request.params.id);

  const model = await modelService.findOne(modelRepository(database), id,(request.user as any).email);

  if (!model) {
    throw createHttpError(404, 'Invalid model id');
  }

  return model;
};

/**
 * Attempts to insert a model into the database.
 * @param database the database in which to insert the model.
 * @param request the Express request object.
 * @returns an object containing the id of the newly inserted model.
 */
const create = async (database: Db, request: Request): Promise<{ id: ObjectId }> => {
  const model = validateSchema(modelSchema, request.body as Model);
  return modelService.create(modelRepository(database), model, (request.user as any).email);
};

/**
 * Attempts to update a model in the database.
 * @param database the database in which to update the model.
 * @param request the Express request object.
 * @returns the number of matched models by the update query.
 */
const update = async (database: Db, request: Request): Promise<number> => {
  const [ id ] = validateRouteParams(request.params.id);
  const model = validateSchema(modelSchema, request.body as Model);
  return modelService.update(modelRepository(database), id, model, (request.user as any).email);
};

/**
 * Attempts to remove a model from the database.
 * @param database the database in which to remove the model.
 * @param request the Express request object.
 * @returns the number of deleted models.
 */
const remove = async (database: Db, request: Request): Promise<number> => {
  const [ id ] = validateRouteParams(request.params.id);
  return modelService.remove(modelRepository(database), id, (request.user as any).email);
};

export default { list, create, update, remove, findOne };
