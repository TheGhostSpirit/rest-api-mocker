import faker from 'faker';

const string = () => {
  return faker.lorem.word();
};

const number = (max?: number) => {
  return faker.random.number(max);
};

const date = () => {
  return faker.date.past();
};

export default {
  string,
  number,
  date
};
