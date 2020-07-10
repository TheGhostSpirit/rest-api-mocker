import * as yaml from 'yaml';

const toJson = (
  document: string
): string => {
  return yaml.parse(document);
};

const toYaml = (
  document: string
): string => {
  return yaml.stringify(document);
};

export default { toJson, toYaml };
