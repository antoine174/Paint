import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Header } from './components/header/header';
import {Canvas} from './components/canva/canvas.component';
import {ShapeConfig} from 'konva/lib/Shape';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Canvas],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  prevShapes: {type: string, config: ShapeConfig }[]= [
    /*{
      type: 'rect',
      config: {
        x: 100,
        y: 100,
        height: 100,
        width: 200,
        fill: 'red',
        stroke: "black",
        strokeWidth: 2,
        draggable: true,
        scaleX: 1,
        scaleY: 1,
        name: (new Date().getTime()).toString()
      }
    }*/
  ]
}
