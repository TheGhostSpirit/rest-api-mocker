const {MongoClient} = require('mongodb');
import modelsController from '../api/models/models.controller';
import { ObjectId } from 'mongodb';

describe('models controller', () => {
  let connection: any;
  let db: any;
  
  const mockModel = { 
    _id: new ObjectId("507f1f77bcf86cd799439011"),
    owner: "toto@toto.com",
    version: 0.1,
    api: { 
      name: "some-model",
      description: "this is a description...",
      contact: {
        name: "jean",
        email: "jean@gmail.com"
      },
      license: "MIT",
      version: 1
    }
  };

  const resultModel = {
    _id: mockModel._id,
    api: mockModel.api,
    version: mockModel.version
  };


  beforeAll(async () => {
    connection = await MongoClient.connect(process.env.MONGO_URL, {
      useNewUrlParser: true,
      useUnifiedTopology: true
    });
    db = await connection.db();
  });
  
  afterAll(async () => {
    await connection.close();
  });
  
  afterEach(async() => {
    db.dropDatabase();
  });

  it('should get models list', async () => {
    const models = db.collection('models');
    await models.insertOne(mockModel)

    expect(await modelsController.list(db, { user: { email: mockModel.owner } } as any)).toEqual([resultModel]);
  });

  it('should find a model', async () => {
    const models = db.collection('models');
    await models.insertOne(mockModel)

    expect(await modelsController.findOne(db, { user: { email: mockModel.owner }, params: { id: mockModel._id } } as any)).toEqual(resultModel);
  });
  
  it('should create model', async () => {
    const models = db.collection('models');
    const createdId = await modelsController.create(db, { user: { email: mockModel.owner }, body: { api: mockModel.api, version: mockModel.version} } as any)
    const createdModel = await models.findOne({ _id: createdId.id });

    expect(createdModel).toEqual({ _id: createdId.id, version: mockModel.version, api: mockModel.api, owner: mockModel.owner });
  });
  
  it('should update model', async () => {
    const models = db.collection('models');
    var newModel = mockModel;
    newModel.api.name = "new-name";
    newModel.api.version++;
    
    await models.insertOne(mockModel)
    await modelsController.update(db, { user: { email: mockModel.owner }, params: { id: mockModel._id }, body: { api: newModel.api, version: newModel.version }} as any)
    
    expect(await models.findOne({ _id: mockModel._id })).toEqual(newModel);
  });
  
  it('should remove model', async () => {
    const models = db.collection('models');
    await models.insertOne(mockModel)
    
    expect(await modelsController.remove(db, { user: { email: mockModel.owner }, params: { id: mockModel._id }} as any)).toEqual(1);
  });
});