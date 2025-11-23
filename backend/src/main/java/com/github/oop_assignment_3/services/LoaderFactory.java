package com.github.oop_assignment_3.services;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.github.oop_assignment_3.models.Loader;

@Service
public class LoaderFactory {
	private final Map<String, Loader> loaders;

	public LoaderFactory(Map<String, Loader> savers) {
		this.loaders = savers;
	}

	public Loader getLoader(String format) {
		Loader loader = loaders.get(format + "Loader");
		if (loader == null) {
			throw new IllegalArgumentException(
					"No loader found for format: " + format);
		}

		return loader;
	}

}
