package com.github.oop_assignment_3.models;

import java.io.IOException;

public interface Saver {
	String save(Drawing drawing) throws IOException;
}
