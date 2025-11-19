import { Component } from '@angular/core';
import { Drawing } from '../../services/drawing';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header {
  colors = [
    { key: 'red', value: 'var(--color-red-500)' },
    { key: 'blue', value: 'var(--color-blue-500)' },
    { key: 'green', value: 'var(--color-green-500)' },
    { key: 'yellow', value: 'var(--color-yellow-400)' },
    { key: 'purple', value: 'var(--color-purple-500)' },
    { key: 'pink', value: 'var(--color-pink-500)' },
    { key: 'orange', value: 'var(--color-orange-500)' },
    { key: 'cyan', value: 'var(--color-cyan-500)' },
    { key: 'gray', value: 'var(--color-gray-800)' },
    { key: 'white', value: 'var(--color-white)' },
  ] as const;

  constructor(public drawing: Drawing) {}

  setColor(color: string) {
    this.drawing.SelectedColor = color;
    console.log(this.drawing.SelectedColor);
  }
}
