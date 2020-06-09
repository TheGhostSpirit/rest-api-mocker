import { ObjectId } from 'mongodb';

import { Repository } from '../../utils';

import { Model } from './model';

/**
 * Retrieves the list of products from the product collection.
 * @param repository the repository that interfaces the product collection.
 * @returns a list of products.
 */
const list = async (
  repository: Repository<Model>,
  user: string
): Promise<Model[]> => {
  return repository.find({ owner: user }, { version: 1, api: 1, _id: 0 });
};

/**
 * Retrieves one product from the product collection.
 * @param repository the repository that interfaces the product collection.
 * @param id the id of the product to look for.
 * @returns the product that matches the given id or null if it is not found.
 */
const findOne = async (
  repository: Repository<Model>,
  id: ObjectId
): Promise<Model | null> => {
  return repository.findById(id);
};

/**
 * Inserts a new Product in the product collection.
 * @param repository the repository that interfaces the product collection.
 * @param product the product to insert.
 * @returns the ObjectId of the newly inserted product.
 */
const create = async (
  repository: Repository<Model>,
  product: Model
): Promise<{ id: ObjectId }> => {
  return repository.insertOne(product);
};

/**
 * Updates a product in the product collection.
 * @param repository the repository that interfaces the product collection.
 * @param id the id of the product to update.
 * @param product the new body of the product.
 * @returns the amount of documents matched by the given id in the product collection.
 */
const update = async (
  repository: Repository<Model>,
  id: ObjectId,
  product: Partial<Model>
): Promise<number> => {
  return repository.updateOne(id, { $set: product });
};

/**
 * Removes a product from the product collection.
 * @param repository the repository that interfaces the product collection.
 * @param id the id of the product to remove.
 * @returns the amount of deleted documents.
 */
const remove = async (
  repository: Repository<Model>,
  id: ObjectId
): Promise<number> => {
  return repository.deleteOne(id);
};

export default { list, findOne, create, update, remove };
