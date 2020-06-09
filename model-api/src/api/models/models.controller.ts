import { Db, ObjectId } from 'mongodb';
import { Request } from 'express';
import createHttpError from 'http-errors';

import { validateSchema, validateRouteParams, Repository, useRepository } from '../../utils';

import { Model } from './model';
import modelService from './models.service';
import modelSchema from './models.schema';

/**
 * Returns the repository associated with the product collection.
 * @returns the product repository.
 */
const modelRepository = (database: Db): Repository<Model> => useRepository(database, 'models');

/**
 * Retrieves the list of products from the database.
 * @param database the database in which to look for products.
 * @returns a list of products.
 */
const list = async (database: Db, request: Request): Promise<Model[]> => {
  return modelService.list(modelRepository(database), (request.user as any).email);
};

/**
 * Retrieves one product from the database.
 * @param database the database in which to find the product.
 * @param request the Express request object.
 * @returns one product matching the given id.
 * @throws a 404 http error if the id is invalid.
 */
const findOne = async (database: Db, request: Request): Promise<Model> => {
  const [ id ] = validateRouteParams(request.params.id);

  const product = await modelService.findOne(modelRepository(database), id);

  if (!product) {
    throw createHttpError(404, 'Invalid product id');
  }

  return product;
};

/**
 * Attempts to insert a product into the database.
 * @param database the database in which to insert the product.
 * @param request the Express request object.
 * @returns an object containing the id of the newly inserted product.
 */
const create = async (database: Db, request: Request): Promise<{ id: ObjectId }> => {
  const product = validateSchema(modelSchema, request.body as Model);
  return modelService.create(modelRepository(database), product);
};

/**
 * Attempts to update a product in the database.
 * @param database the database in which to update the product.
 * @param request the Express request object.
 * @returns the number of matched products by the update query.
 */
const update = async (database: Db, request: Request): Promise<number> => {
  const [ id ] = validateRouteParams(request.params.id);
  const product = validateSchema(modelSchema, request.body as Model);
  return modelService.update(modelRepository(database), id, product);
};

/**
 * Attempts to remove a product from the database.
 * @param database the database in which to remove the product.
 * @param request the Express request object.
 * @returns the number of deleted products.
 */
const remove = async (database: Db, request: Request): Promise<number> => {
  const [ id ] = validateRouteParams(request.params.id);
  return modelService.remove(modelRepository(database), id);
};

export default { list, create, update, remove, findOne };
