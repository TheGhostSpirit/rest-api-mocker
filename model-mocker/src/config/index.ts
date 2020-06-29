import dotenv from 'dotenv';

dotenv.config({ path: '../.env' });

/**
 * Application configuration object.
 */
export const CONFIG = {
  port: process.env.MODEL_MOCKER_PORT,
  database: {
    port: process.env.MODEL_MOCKER_DATABASE_PORT,
    host: process.env.MODEL_MOCKER_DATABASE_HOST,
    dbName: process.env.MODEL_MOCKER_DATABASE_DBNAME,
    get url(): string {
      return `mongodb://${this.host}:${this.port}`;
    }
  }
};
