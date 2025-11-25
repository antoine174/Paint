import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class Drawing {
  SelectedColor: string = 'black';
  SelectedStroke: string = 'black';
  SelectedStrokeWidth: number = 4;
}
