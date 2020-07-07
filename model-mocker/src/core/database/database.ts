import { MongoClient, Db } from 'mongodb';

import { CONFIG } from '../../config';

/**
 * Attempts to establish a connection with the Mongo database.
 * @returns a database object if the connection is established.
 */
const connect = async (url?: string, dbName?: string): Promise<Db> => {
  const client = await MongoClient.connect(url ?? CONFIG.database.url,
    { useNewUrlParser: true, useUnifiedTopology: true }
  );
  return client.db(dbName ?? CONFIG.database.dbName);
};

export const Database = {
  connect
};
