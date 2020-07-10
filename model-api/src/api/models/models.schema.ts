import * as yup from 'yup';

import { Model } from './model';
import { ParamField, QueryField, BodyField, ResponseBodyField } from 'model-share/types/model';

const paramFieldSchema: yup.ObjectSchema<ParamField> = yup.object().shape<ParamField>({
  name: yup.string().required(),
  type: yup.string().required()
});

const queryFieldSchema: yup.ObjectSchema<QueryField> = yup.object().shape<QueryField>({
  name: yup.string().required(),
  type: yup.string().required(),
  required: yup.boolean(),
});

const bodyFieldSchema: yup.ObjectSchema<BodyField> = yup.object().shape<BodyField>({
  name: yup.string().required(),
  type: yup.string().required(),
  required: yup.boolean(),
  properties: yup.array().of(
    yup.lazy(() => bodyFieldSchema.default(undefined))
  ),
  items: yup.array().of(
    yup.lazy(() => bodyFieldSchema.default(undefined))
  )
});

const responseBodyFieldSchema: yup.ObjectSchema<ResponseBodyField> = yup.object().shape<ResponseBodyField>({
  name: yup.string().required(),
  type: yup.string().required(),
  value: yup.mixed(),
  properties: yup.array().of(
    yup.lazy(() => responseBodyFieldSchema.default(undefined))
  ),
  items: yup.array().of(
    yup.lazy(() => responseBodyFieldSchema.default(undefined))
  )
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
        query: yup.array().of(queryFieldSchema),
        params: yup.array().of(paramFieldSchema),
        body: yup.array().of(bodyFieldSchema),
        response: yup.array().of(
          yup.object().shape({
            status: yup.number().required(),
            body: yup.array().of(responseBodyFieldSchema)
          })
        )
      })
    )
  })
});

export default modelSchema;
