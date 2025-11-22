package com.github.oop_assignment_3.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.oop_assignment_3.serialization.CamelCaseUnwrappingSerializerModifier;

@Configuration
public class ApplicationConfiguration {
	@Bean
	public Module camelCaseUnwrappingModule() {
		SimpleModule module = new SimpleModule();
		module.setSerializerModifier(new CamelCaseUnwrappingSerializerModifier());
		return module;
	}
}
