package com.github.oop_assignment_3.models;

import java.io.IOException;

public interface Loader {
	Drawing load(String path) throws IOException;
}
