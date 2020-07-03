import dotenv from 'dotenv';

dotenv.config({ path: '../.env' });

/**
 * Application configuration object.
 */
export const CONFIG = {
  port: process.env.MODEL_MOCKER_PORT,
  model: {
    repository: process.env.MODEL_MOCKER_REPOSITORY ?? '',
    name: process.env.MODEL_MOCKER_DEFAULT_MODEL,
    get fullPath(): string {
      return `${this.repository}/${this.name}`;
    }
  },
  database: {
    port: process.env.MODEL_MOCKER_DATABASE_PORT,
    host: process.env.MODEL_MOCKER_DATABASE_HOST,
    dbName: 'toto',
    get url(): string {
      return `mongodb://${this.host}:${this.port}`;
    }
  }
};
