package com.github.oop_assignment_3.controller;

import com.github.oop_assignment_3.dtos.actions.CreateActionDTO;
import com.github.oop_assignment_3.dtos.actions.DeleteActionDTO;
import com.github.oop_assignment_3.dtos.actions.MoveActionDTO;
import com.github.oop_assignment_3.dtos.actions.TransformActionDTO;
import com.github.oop_assignment_3.models.Shape;
import com.github.oop_assignment_3.services.PaintService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paint")
@CrossOrigin(origins = "*")  // you can limit this to your frontend url
public class PaintController {

    private final PaintService paintService;

    @Autowired
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
}
