package com.github.oop_assignment_3.services;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.github.oop_assignment_3.models.Saver;

@Service
public class SaverFactory {
	private final Map<String, Saver> savers;

	public SaverFactory(Map<String, Saver> savers) {
		this.savers = savers;
	}

	public Saver getSaver(String format) {
		Saver saver = savers.get(format + "Saver");
		if (saver == null) {
			throw new IllegalArgumentException(
					"No saver found for format: " + format);
		}

		return saver;
	}

}
