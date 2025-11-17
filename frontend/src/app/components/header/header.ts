import { Component } from '@angular/core';
import { Drawing } from '../../services/drawing';

@Component({
  selector: 'app-header',
  templateUrl: './header.html',
  styleUrls: ['./header.css'],
})
export class Header {

  constructor(public drawing: Drawing) {}

  setColor(color: string) {
    this.drawing.SelectedColor = color;
    console.log(this.drawing.SelectedColor);
  }
}
