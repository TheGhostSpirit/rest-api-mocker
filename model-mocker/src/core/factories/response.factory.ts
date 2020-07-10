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

const getFieldValue = (field: ObjectField) => {
  const map = new Map<string, any>([
    ['items', [build(field.items ?? []), build(field.items ?? [])]],
    ['properties', build(field.properties ?? [])],
    ['value', field.value]
  ]);

  return [ ...map.entries() ].find(([k]) => k in field)?.[1] ?? randomOfType(field.type);
};

const build = (fields: ObjectField[]): any => {
  return fields
    .reduce(
      (obj: Object, f: ObjectField) => ({ ...obj, [f.name]: getFieldValue(f) }), {}
    );
};

export const ResponseFactory = {
  build
};
