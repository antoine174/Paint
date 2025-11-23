import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Header } from './components/header/header';
import {Canvas} from './components/canva/canvas.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Canvas],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {}
