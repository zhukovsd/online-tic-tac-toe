/*
 * Copyright 2017 Zhukov Sergey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhukovsd;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;

/**
 * Created by ZhukovSD on 19.06.2017.
 */
public class SynchronizedSerializer extends JsonSerializer {
    static class BeanSerializerFactoryWithVisibleConstructingMethod extends BeanSerializerFactory {
        // expose protected constructor
        BeanSerializerFactoryWithVisibleConstructingMethod() {
            super(BeanSerializerFactory.instance.getFactoryConfig());
        }

        @Override
        public JsonSerializer<Object> constructBeanSerializer(SerializerProvider prov, BeanDescription beanDesc) throws JsonMappingException {
            return super.constructBeanSerializer(prov, beanDesc);
        }
    }

    private final BeanSerializerFactoryWithVisibleConstructingMethod factory = new BeanSerializerFactoryWithVisibleConstructingMethod();

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        JavaType type = TypeFactory.defaultInstance().constructType(value.getClass());
        BeanDescription beanDescription = provider.getConfig().introspect(type);
        JsonSerializer<Object> defaultSerializer = factory.constructBeanSerializer(provider, beanDescription);

        synchronized (value) {
            defaultSerializer.serialize(value, gen, provider);
        }
    }
}
