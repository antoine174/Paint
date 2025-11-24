import {AfterViewInit, Component, inject, signal} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {Canvas} from './components/canva/canvas.component';
import {ShapeConfig} from 'konva/lib/Shape';
import {Http} from './services/http/http';
import {NgClass} from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [Canvas, NgClass],
  templateUrl: './app.html',
  styleUrl: './app.css',
  standalone: true
})
export class App implements AfterViewInit{
  http = inject(Http)
  running = signal(false);
  stage = signal<ShapeConfig[]>([])

  handleNew() {
    this.http.newDrawing().subscribe({
      next: (res) => {
        console.log("new drawing")
      },
      error: (e) => console.log(e)
    })
    this.running.set(true)
  }
  handleLoad = (e: any) => {
  const selectedFile = e?.target?.files[0]
  const fileName = (selectedFile.name as string);
  const format = fileName.includes("json")? "json":
    fileName.includes("xml")? "xml": "";
  const fileReader = new FileReader()
  fileReader.onload = (e) => {
    if(["json", "xml"].includes(format)) this.http.loadDrawing(format, e?.target?.result as string)
      .subscribe({
        next: (d) => {
          console.log(d)
          this.stage.set(d as any)
          this.running.set(true)
        },
        error: (e) => {
          console.log(e)
        }
      })
    else {
    }
  }
  fileReader.readAsText(selectedFile)
}
  ngAfterViewInit() {

    const file = document.getElementById("fileInput")


    // console.log(file)
    // file?.addEventListener('change', )


  }


}


