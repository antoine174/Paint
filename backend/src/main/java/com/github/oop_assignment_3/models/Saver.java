package com.github.oop_assignment_3.models;

import java.io.IOException;

public interface Saver {
	void save(Drawing drawing, String path) throws IOException;
}
