import { random } from '../../utils';
import { ObjectField } from '../models';

const randomOfType = (type: string) => {
  return new Map<string, Function>([
    ['string', random.string],
    ['number', random.number],
    ['date', random.date]
  ])
    .get(type)?.();
};

const build = (fields: ObjectField[]): any => {
  return fields
    .reduce(
      (obj: Object, f: ObjectField) => {
        const randomValue = !f.items
          ? f.properties
            ? build(f.properties)
            : randomOfType(f.type)
          : [build(f.items), build(f.items)];
        return { ...obj, [f.name]: randomValue };
      },
      {}
    );
};

export const ResponseFactory = {
  build
};
