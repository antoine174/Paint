package com.github.oop_assignment_3.models.savers;

import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.oop_assignment_3.models.Drawing;
import com.github.oop_assignment_3.models.Saver;

@Component
public class JsonSaver implements Saver {
	private final JsonMapper mapper;

	public JsonSaver() {
		this.mapper = new JsonMapper();
	}

	@Override
	public void save(Drawing drawing, String path) throws IOException {
		mapper.writeValue(new File(path), drawing);
	}
}
