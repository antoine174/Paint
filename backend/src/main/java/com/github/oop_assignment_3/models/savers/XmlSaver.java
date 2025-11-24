package com.github.oop_assignment_3.models.savers;

import java.io.IOException;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.oop_assignment_3.models.Drawing;
import com.github.oop_assignment_3.models.Saver;

@Component
public class XmlSaver implements Saver {
	private final XmlMapper mapper;

	public XmlSaver() {
		this.mapper = new XmlMapper();
	}

	@Override
	public String save(Drawing drawing) throws IOException {
		return mapper.writeValueAsString(drawing);
	}
}
