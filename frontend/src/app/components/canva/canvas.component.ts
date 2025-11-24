import {
  AfterViewInit,
  Component,
  OnInit,
  ViewChild,
  WritableSignal,
  signal,
  input, inject
} from '@angular/core';
import {CoreShapeComponent, StageComponent, NgKonvaEventObject} from 'ng2-konva';
import {ShapeConfig} from 'konva/lib/Shape';
import {ContainerConfig} from 'konva/lib/Container';
import {Header} from '../header/header';
import {Drawing} from '../../services/drawing/drawing';
import Konva from 'Konva'
import {Action, AddShape, DeleteShape, Move, Transform} from './Actions';
import {Http} from '../../services/http/http';

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

  constructor(public drawing: Drawing) {
  }

  handleRedo() {
    this.http.redo()
      .subscribe({
        next: (d) => {
          this.shapesSignal().set(d as any[])
        }
      })
  }
  handleUndo() {
    this.http.undo()
      .subscribe({
        next: (d) => {
          this.shapesSignal().set(d as any[])
        }
      })
  }
  handleDelete() {
    const shapesInTransformer = this.transformerNode.nodes();
    if(shapesInTransformer.length == 0) return;
    const shapeConfig = shapesInTransformer[0];
    console.log(shapeConfig)
    this.http.deleteShape(shapeConfig.attrs.name)
      .subscribe({
        next: (d) => {
          this.shapesSignal().set(d as any[])
        }
      })
    this.transformerNode.nodes([])
  }
  format = signal<"json" | "xml">("json")
  handleSave() {
    this.http.save(this.format())
      .subscribe({
        next: (d) => {
          console.log(d)
          downloadFile(`save.${this.format()}`, (d as any).data)
        }
      })
  }
  stageConfig: ContainerConfig = {
    height: 1000,
    width: 1200,
  }
  stageNode!: Konva.Stage
  transformerNode!: Konva.Transformer
  actions: Action[] = []
  http = inject(Http);
   ngAfterViewInit(): void {
     this.stageNode = (this.stage as any).getNode();
     this.transformerNode = (this.tr as any).getNode();
     this.transformerNode.on("transformend", () => {
       console.log('transformend')
       const shape = this.transformerNode.nodes()[0]
       const newAttrs = shape.attrs
       if(newAttrs) {
         this.http.transformShape(newAttrs.name, newAttrs.x, newAttrs.y, newAttrs.rotation, newAttrs.scaleX, newAttrs.scaleY)
           .subscribe({
             next: (d) => {
               this.shapesSignal().set(d as any[])
             }
           })
       }
     })


   }

   shapesSignal= input.required<WritableSignal<any[]>>();
  handleCreateShape(type: string) {
    if(type != 'shapes') {
      this.http.createShape(type, this.drawing.SelectedColor).subscribe({
        next: (d) => {
          this.shapesSignal().set(d as any[])
        }
      })
    } else {
      console.log(this.shapesSignal()())
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
      case 'save': {
        console.log("save")
        this.handleSave()
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
    const newAttrs = target.attrs
    this.http.moveShape(newAttrs.name, newAttrs.x, newAttrs.y).subscribe({
      next: (d) => {
        this.shapesSignal().set(d as any[])
      }
    })
  }

}

export function downloadFile(filename: string, text: string) {
  const element = document.createElement('a');
  element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
  element.setAttribute('download', filename);

  element.style.display = 'none';
  document.body.appendChild(element);

  element.click();

  document.body.removeChild(element);
}
