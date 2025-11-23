import {
  AfterViewInit,
  Component,
  OnInit,
  ViewChild,
  WritableSignal,
  signal,
  input
} from '@angular/core';
import {CoreShapeComponent, StageComponent, NgKonvaEventObject} from 'ng2-konva';
import {ShapeConfig} from 'konva/lib/Shape';
import {ContainerConfig} from 'konva/lib/Container';
import {Header} from '../header/header';
import {Drawing} from '../../services/drawing/drawing';
import Konva from 'Konva'
import {Action, AddShape, DeleteShape, Move, Transform} from './Actions';

@Component({
  selector: 'app-canvas',
  imports: [
    CoreShapeComponent,
    StageComponent,
    Header
  ],
  templateUrl: './canvas.component.html',
  styleUrl: './canvas.component.css',
})
export class Canvas implements OnInit, AfterViewInit{
  @ViewChild("transformer", {static: true}) tr!: Transformer;
  @ViewChild("stageComponent", {static: true}) stage!: Transformer;
  previousShapes: {type: string, config: ShapeConfig }[] = []
  prevShapes = input<typeof this.previousShapes>([])
  constructor(public drawing: Drawing) {
    console.log(this.prevShapes())
  }
  actionsIndex: number = -1
  canUndo() {
    return this.actionsIndex >= 0;
  }
  canRedo() {
    return this.actionsIndex < this.actions.length - 1
  }
  handleRedo() {
    if(this.canRedo()) {
      const action = this.actions[++this.actionsIndex];
      console.log(action)
      action.apply(this.shapes)
      console.log(this.actionsIndex)
    } else console.log("can't redo")
  }
  handleUndo() {
    if(this.canUndo()) {
      const action = this.actions[this.actionsIndex];
      this.actionsIndex--;
      console.log(action)
      action.undo(this.shapes)
      console.log(this.actionsIndex)
    } else console.log("can't undo")
  }
  handleDelete() {
    const shapesInTransformer = this.transformerNode.nodes();
    if(shapesInTransformer.length == 0) return;
    const shapeConfig = shapesInTransformer[0];
    const config = this.shapes().find((s) => s.config.name == shapeConfig.name as any)

    const deleteAction = new DeleteShape(shapeConfig.attrs.name)
    deleteAction.deletedShape = config
    deleteAction.apply(this.shapes)
    this.actions.push(deleteAction)
    this.transformerNode.nodes([])
    this.actionsIndex++;
  }
  stageConfig: ContainerConfig = {
    height: 1000,
    width: 1200,
  }
  stageNode!: Konva.Stage
  transformerNode!: Konva.Transformer
  actions: Action[] = []
   ngAfterViewInit(): void {
     this.shapes.set(this.prevShapes())
     this.stageNode = (this.stage as any).getNode();
     this.transformerNode = (this.tr as any).getNode();
     this.transformerNode.on("transformend", () => {
       console.log('transformend')
       const shape = this.transformerNode.nodes()[0]
       const newAttrs = shape.attrs
       const oldAttr = this.shapes().find((s) => s.config.name == newAttrs.name)?.config
       // the action
       if(newAttrs && oldAttr) {
         const transform = new Transform(
           newAttrs.name,
           oldAttr.x || 0,
           oldAttr.y || 0,
           newAttrs.x,
           newAttrs.y,
           oldAttr.rotation || 0,
           newAttrs.rotation,
           oldAttr.scaleX || 1,
           oldAttr.scaleY || 1,
           newAttrs.scaleX,
           newAttrs.scaleY
         )
         transform.apply(this.shapes);
         this.actions.push(transform);
         this.actionsIndex++;
       }
     })
     for (let action of this.actions) {
      action.apply(this.shapes)
    }

   }
  shapes: WritableSignal<{type: string, config: ShapeConfig}[]> = signal([]);
  handleCreateShape(type: string) {
    if(type != 'shapes') {
      this.actions = this.actions.slice(0, this.actionsIndex + 1)
      const addAction = new AddShape(type, new Date().getTime().toString(), this.drawing.SelectedColor)
      addAction.apply(this.shapes)
      this.actions.push(addAction)
      this.actionsIndex++;
    } else {
      console.log(this.shapes)
      console.log(this.actions)
    }
  }
  handleAction(action: string) {
    switch (action) {
      case "redo": {
        this.handleRedo()
        break
      }
      case "undo": {
        this.handleUndo()
        break
      }
      case 'delete': {
        this.handleDelete()
        break;
      }
    }
  }
  ngOnInit(): void {
   }
  handleShapeClick(e: NgKonvaEventObject<MouseEvent>) {
    e.event.evt.stopPropagation()
    this.transformerNode.stopDrag()
    const shape = e.event.target;
    this.transformerNode.nodes([shape])
    this.transformerNode.getLayer()?.batchDraw();
    console.log(shape)
  }

  handleStageClick(e: any) {
    if(!e || !e.target) return;
    if(e.target == e.target.getStage()) {
      this.transformerNode.nodes([])
      this.transformerNode.getLayer()?.batchDraw();
    }
  }
  handleDragStart(_e: NgKonvaEventObject<MouseEvent>) {
    //console.log(e.event.target)
  }
  handleDragEnd(e: NgKonvaEventObject<MouseEvent>) {
    const target = e.event.target
    console.log("target", target);
    const shapesArr = this.shapes();
    const shape = shapesArr.find((s) => s.config.name == target.attrs.name as any)
    if (shape && shape.config) {
      const config = shape.config
      const moveAction = new Move(config.name as string, shape.config.x || 0, shape.config.y || 0, target.attrs.x, target.attrs.y);
      moveAction.apply(this.shapes);
      this.actions = this.actions.slice(0, this.actionsIndex + 1);
      this.actions.push(moveAction);
      this.actionsIndex++;
    }
    console.log(shape)
  }

}
