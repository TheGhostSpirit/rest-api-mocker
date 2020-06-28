import * as yup from 'yup';

import { Model, ObjectField } from './model';

/**
 * Validation Schema for object fields.
 */
const objectFieldSchema = yup.object().shape<ObjectField>({
  name: yup.string().required(),
  type: yup.string().required(),
  required: yup.boolean()
});

/**
 * Validation Schema for Model instances.
 */
const modelSchema = yup.object().shape<Model>({
  version: yup.number().required(),
  api: yup.object().shape({
    name: yup.string().required(),
    description: yup.string().required(),
    contact: yup.object().shape({
      name: yup.string().required(),
      email: yup.string().email().required()
    }),
    license: yup.string().required(),
    version: yup.number().required(),
    routes: yup.array().of(
      yup.object().shape({
        path: yup.string().required(),
        method: yup.string().required(),
        query: yup.array().of(objectFieldSchema),
        params: yup.array().of(objectFieldSchema),
        body: yup.array().of(objectFieldSchema),
        response: yup.array().of(
          yup.object().shape({
            status: yup.number().required(),
            body: yup.array().of(objectFieldSchema)
          })
        )
      })
    )
  })
});

export default modelSchema;
