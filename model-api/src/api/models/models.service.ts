import { ObjectId } from 'mongodb';
import fs from 'fs';

import { Repository } from '../../utils';

import { Model } from './model';
import { CONFIG } from '../../config';

/**
 * Retrieves the list of models from the model collection.
 * @param repository the repository that interfaces the model collection.
 * @param user the user performing this action.
 * @returns a list of models.
 */
const list = async (
  repository: Repository<Model>,
  user: string
): Promise<Model[]> => {
  return repository.find({ owner: user }, { 'api.routes': 0, owner: 0 });
};

/**
 * Retrieves one model from the model collection.
 * @param repository the repository that interfaces the model collection.
 * @param id the id of the model to look for.
 * @param user the user performing this action.
 * @returns the model that matches the given id or null if it is not found.
 */
const findOne = async (
  repository: Repository<Model>,
  id: ObjectId,
  user: string
): Promise<Model | null> => {
  return repository.findOne({ _id: id, owner: user }, { owner: 0 });
};

/**
 * Inserts a new Model in the model collection.
 * @param repository the repository that interfaces the model collection.
 * @param model the model to insert.
 * @param user the user performing this action.
 * @returns the ObjectId of the newly inserted model.
 */
const create = async (
  repository: Repository<Model>,
  model: Model,
  user: string
): Promise<{ id: ObjectId }> => {
  return repository.insertOne({ ...model, owner: user });
};

/**
 * Updates a model in the model collection.
 * @param repository the repository that interfaces the model collection.
 * @param id the id of the model to update.
 * @param model the new body of the model.
 * @param user the user performing this action.
 * @returns the amount of documents matched by the given id in the model collection.
 */
const update = async (
  repository: Repository<Model>,
  id: ObjectId,
  model: Partial<Model>,
  user: string
): Promise<number> => {
  return repository.updateOne({ _id: id, owner: user }, { $set: model });
};

/**
 * Removes a model from the model collection.
 * @param repository the repository that interfaces the model collection.
 * @param id the id of the model to remove.
 * @param user the user performing this action.
 * @returns the amount of deleted documents.
 */
const remove = async (
  repository: Repository<Model>,
  id: ObjectId,
  user: string
): Promise<number> => {
  return repository.deleteOne({ _id: id, owner: user });
};

const deploy = async(
  repository: Repository<Model>,
  id: ObjectId,
  user: string
): Promise<boolean> => {
  const model = await findOne(repository, id, user);

  if (!model) {
    return false;
  }

  await new Promise(resolve => {
    fs.writeFile(CONFIG.model.fullPath, JSON.stringify(model), {}, () => resolve());
  });

  return true;
};

export default { list, findOne, create, update, remove, deploy };
