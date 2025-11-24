package com.github.oop_assignment_3.models.loaders;

import java.io.IOException;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.oop_assignment_3.models.Drawing;
import com.github.oop_assignment_3.models.Loader;

@Component
public class JsonLoader implements Loader {
	private final JsonMapper mapper;

	public JsonLoader() {
		this.mapper = new JsonMapper();
	}


	@Override
	public Drawing load(String data) throws IOException {
		return mapper.readValue(data, Drawing.class);
	}
}
