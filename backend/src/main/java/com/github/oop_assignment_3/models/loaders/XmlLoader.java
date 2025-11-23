package com.github.oop_assignment_3.models.loaders;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.oop_assignment_3.models.Drawing;
import com.github.oop_assignment_3.models.Loader;

public class XmlLoader implements Loader {
	private final XmlMapper mapper;

	public XmlLoader() {
		this.mapper = new XmlMapper();
	}


	@Override
	public Drawing load(String path) throws IOException {
		return mapper.readValue(new File(path), Drawing.class);
	}

}
