package com.github.oop_assignment_3.serialization;

import java.io.IOException;
import org.springframework.util.StringUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.util.NameTransformer;

public class CamelCaseUnwrappingSerializerModifier
		extends BeanSerializerModifier {
	@Override
	public JsonSerializer<?> modifySerializer(SerializationConfig config,
			BeanDescription beanDesc, JsonSerializer<?> serializer) {
		return new CamelCaseUnwrappingSerializer<>(serializer);
	}

	private static class CamelCaseUnwrappingSerializer<T>
			extends JsonSerializer<T> {
		private final JsonSerializer<T> defaultSerializer;

		public CamelCaseUnwrappingSerializer(JsonSerializer<T> defaultSerializer) {
			this.defaultSerializer = defaultSerializer;
		}

		@Override
		public void serialize(T value, JsonGenerator gen,
				SerializerProvider provider) throws IOException {
			defaultSerializer.serialize(value, gen, provider);
		}

		@Override
		public JsonSerializer<T> unwrappingSerializer(NameTransformer unwrapper) {
			NameTransformer custom =
					NameTransformer.chainedTransformer(unwrapper, new NameTransformer() {
						@Override
						public String transform(String name) {
							return StringUtils.capitalize(name);
						}

						@Override
						public String reverse(String transformed) {
							return StringUtils.uncapitalize(transformed);
						}
					});

			if (unwrapper.transform("").isEmpty()) {
				return defaultSerializer.unwrappingSerializer(unwrapper);
			} else {
				return defaultSerializer.unwrappingSerializer(custom);
			}
		}

		@Override
		public JsonSerializer<T> replaceDelegatee(JsonSerializer<?> delegatee) {
			return defaultSerializer.replaceDelegatee(delegatee);
		}

		@Override
		public void serializeWithType(T value, JsonGenerator gen,
				SerializerProvider serializers, TypeSerializer typeSer)
				throws IOException {
			defaultSerializer.serializeWithType(value, gen, serializers, typeSer);
		}

		@Override
		public Class<T> handledType() {
			return defaultSerializer.handledType();
		}

		@Override
		public boolean isEmpty(SerializerProvider provider, T value) {
			return defaultSerializer.isEmpty(provider, value);
		}

		@Override
		public boolean usesObjectId() {
			return defaultSerializer.usesObjectId();
		}

		@Override
		public boolean isUnwrappingSerializer() {
			return defaultSerializer.isUnwrappingSerializer();
		}

		@Override
		public JsonSerializer<?> getDelegatee() {
			return defaultSerializer.getDelegatee();
		}

		@Override
		public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor,
				JavaType type) throws JsonMappingException {
			defaultSerializer.acceptJsonFormatVisitor(visitor, type);
		}
	}

}

