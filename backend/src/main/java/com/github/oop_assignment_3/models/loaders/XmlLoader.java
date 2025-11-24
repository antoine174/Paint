package com.github.oop_assignment_3.models.loaders;

import java.io.IOException;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.oop_assignment_3.models.Drawing;
import com.github.oop_assignment_3.models.Loader;

@Component
public class XmlLoader implements Loader {
	private final XmlMapper mapper;

	public XmlLoader() {
		this.mapper = new XmlMapper();
	}


	@Override
	public Drawing load(String data) throws IOException {
		return mapper.readValue(data, Drawing.class);
	}
}
