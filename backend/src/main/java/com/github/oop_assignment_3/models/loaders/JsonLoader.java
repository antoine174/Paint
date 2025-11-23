package com.github.oop_assignment_3.models.loaders;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.oop_assignment_3.models.Drawing;
import com.github.oop_assignment_3.models.Loader;

public class JsonLoader implements Loader {
	private final JsonMapper mapper;

	public JsonLoader() {
		this.mapper = new JsonMapper();
	}


	@Override
	public Drawing load(String path) throws IOException {
		return mapper.readValue(new File(path), Drawing.class);
	}

}
