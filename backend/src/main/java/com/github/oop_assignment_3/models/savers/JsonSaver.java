package com.github.oop_assignment_3.models.savers;

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
	public String save(Drawing drawing) throws IOException {
		return mapper.writeValueAsString(drawing);
	}
}
