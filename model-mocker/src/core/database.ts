import { MongoClient, Db, ObjectId } from 'mongodb';

import { CONFIG } from '../config';

/**
 * Attempts to establish a connection with the Mongo database.
 * @returns a database object if the connection is established.
 */
export const databaseConnection = async (): Promise<Db> => {
  const client = await MongoClient.connect(CONFIG.database.url,
    { useNewUrlParser: true, useUnifiedTopology: true }
  );
  return client.db(CONFIG.database.dbName);
};

/**
 * Attempts to convert a string into an ObjectId instance.
 * @param id the string to convert.
 * @return an ObjectID instance or an empty string if the conversion fails.
 */
export const objectId = (id: string | ObjectId) => {
  try {
    return new ObjectId(id);
  } catch({}) {
    return '';
  }
};
