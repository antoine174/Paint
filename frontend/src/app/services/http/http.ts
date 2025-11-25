import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class Http {
  private http = inject(HttpClient)
  baseURL = "http://localhost:8080";
  newDrawing() {
    return this.http.post(`${this.baseURL}/paint/load`, {
        "format": "json",
        "data": "{\"shapes\": [],\"history\": [],\"historyIndex\": 0}"
      }
    )
  }
  loadDrawing(format: string, data: string) {
    return this.http.post(`${this.baseURL}/paint/load`, {
      format,
      data
    })
  }
  createShape(className: string, fill: string) {
    return this.http.post(`${this.baseURL}/paint/create`, {
      x: className == "line"? 0: 100,
      y: className == "line"? 0: 100,
      className,
      fill,
      strokeWidth: className == "line"? 4: 2,
      stroke: className != "line"? "black": fill,
      draggable: true
    })
  }
  moveShape(name: string, newX: number, newY: number) {
    return this.http.post(`${this.baseURL}/paint/move`, {
      name,
      newX,
      newY,
    })
  }
  transformShape(name: string, newx: number, newy: number, newrotation: number, newscaleX: number, newscaleY: number) {
    return this.http.post(`${this.baseURL}/paint/transform`, {
      name,
      newx,
      newy,
      newscaleX,
      newscaleY,
      newrotation
    })
  }
  deleteShape(name: string) {
    return this.http.post(`${this.baseURL}/paint/delete`, {
      name,
    })
  }
  copyShape(name: string) {
    return this.http.post(`${this.baseURL}/paint/copy`, {
      name,
    })
  }
  undo() {
    return this.http.post(`${this.baseURL}/paint/undo`, {})
  }
  redo() {
    return this.http.post(`${this.baseURL}/paint/redo`, {})
  }
  save(format: string) {
    return this.http.post(`${this.baseURL}/paint/save`, {
      format
    })
  }
}
