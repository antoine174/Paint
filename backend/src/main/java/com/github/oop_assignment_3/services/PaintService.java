package com.github.oop_assignment_3.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.github.oop_assignment_3.models.Action;
import com.github.oop_assignment_3.models.Shape;

@Service
public class PaintService {
	Map<Integer, Shape> stage = new HashMap<>();
	List<Action> history = new ArrayList<>();

	List<Shape> getStage() {
		return new ArrayList<>(stage.values());
	}
}
