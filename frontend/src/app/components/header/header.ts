import {AfterViewInit, Component, Input, input, output, WritableSignal} from '@angular/core';
import { Drawing } from '../../services/drawing/drawing';

// @ts-ignore
@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.html',
  styleUrl: './header.css',
  standalone: true
})
export class Header implements AfterViewInit{

  create = output<string>();
  action = output<string>();
  createEmmit(shape: string) {
    this.create.emit(shape)
  }
  handleActionEmmit(action: string) {
    this.action.emit(action)
  }
  format = input.required<WritableSignal<"json" | "xml">>()
  constructor(public drawing: Drawing) {}


  ngAfterViewInit(): void {
    const select = document.getElementById("format")
    select?.addEventListener("change", (e: any) => {
      this.format().set(e.target.value)
    })
  }

  handleColorChange(e: any) {
    this.drawing.SelectedColor = e.target.value
    console.log(e.target.value)
  }
  handleStrokeChange(e: any) {
    console.log(e.target.value)
    this.drawing.SelectedStroke = e.target.value
  }
  handleStrokeWidthChange(e: any) {
    console.log(e.target.value)
    this.drawing.SelectedStrokeWidth = Math.abs(e.target.value)
  }
}
