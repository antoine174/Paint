package com.github.oop_assignment_3.controller;

import com.github.oop_assignment_3.dtos.actions.CopyActionDTO;
import com.github.oop_assignment_3.dtos.actions.CreateActionDTO;
import com.github.oop_assignment_3.dtos.actions.DeleteActionDTO;
import com.github.oop_assignment_3.dtos.actions.MoveActionDTO;
import com.github.oop_assignment_3.dtos.actions.TransformActionDTO;
import com.github.oop_assignment_3.models.Shape;
import com.github.oop_assignment_3.services.PaintService;
import com.github.oop_assignment_3.models.LoadRequest;
import com.github.oop_assignment_3.models.SaveRequest;
import com.github.oop_assignment_3.models.SaveResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/paint")
@CrossOrigin(origins = "http://localhost:4200")
public class PaintController {
	private final PaintService paintService;

	public PaintController(PaintService paintService) {
		this.paintService = paintService;
	}

	@GetMapping("/stage")
	public List<Shape> getStage() {
		return paintService.getStage();
	}

	@PostMapping("/create")
	public List<Shape> create(@RequestBody CreateActionDTO dto) {
		return paintService.create(dto);
	}

	@PostMapping("/delete")
	public List<Shape> delete(@RequestBody DeleteActionDTO dto) {
		return paintService.delete(dto);
	}

	@PostMapping("/copy")
	public List<Shape> copy(@RequestBody CopyActionDTO dto) {
		return paintService.copy(dto);
	}

	@PostMapping("/move")
	public List<Shape> move(@RequestBody MoveActionDTO dto) {
		return paintService.move(dto);
	}

	@PostMapping("/transform")
	public List<Shape> transform(@RequestBody TransformActionDTO dto) {
		return paintService.transform(dto);
	}

	@PostMapping("/undo")
	public List<Shape> undo() {
		return paintService.undo();
	}

	@PostMapping("/redo")
	public List<Shape> redo() {
		return paintService.redo();
	}

	@PostMapping("/save")
	public SaveResponse save(@RequestBody SaveRequest request)
			throws IOException {
		return paintService.save(request);
	}

	@PostMapping("/load")
	public List<Shape> load(@RequestBody LoadRequest request) throws IOException {
		return paintService.load(request);
	}
}
