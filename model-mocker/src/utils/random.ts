import faker from 'faker';

const string = (): string => {
  return faker.lorem.word();
};

const number = (max?: number): number => {
  return faker.random.number(max);
};

const date = (): Date => {
  return faker.date.past();
};

export const random = {
  string,
  number,
  date
};
