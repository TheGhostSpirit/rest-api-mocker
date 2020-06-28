import dotenv from 'dotenv';

dotenv.config();

/**
 * Application configuration object.
 */
export const CONFIG = {
  port: process.env.NODE_PORT,
  database: {
    port: process.env.MONGO_PORT,
    host: process.env.MONGO_HOST,
    dbName: process.env.MONGO_DBNAME,
    get url(): string {
      return `mongodb://${this.host}:${this.port}`;
    }
  }
};
