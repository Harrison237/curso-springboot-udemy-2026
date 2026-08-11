import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ProductComponent } from './components/product/product.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, ProductComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  protected title: string = 'Hola mundo Angular 19!';
  protected enabled: boolean = false;

  courses: Array<string> = ['Angular', 'React', 'Spring Boot'];

  setEnabled() {
    this.enabled = !this.enabled;
  }
}
