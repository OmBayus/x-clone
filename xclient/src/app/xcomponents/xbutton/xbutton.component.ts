import { Component,Input } from '@angular/core';

@Component({
  selector: 'app-xbutton',
  templateUrl: './xbutton.component.html',
  styleUrls: ['./xbutton.component.scss']
})
export class XbuttonComponent {

  @Input() text:string = "";
  @Input() outline:boolean=false;
  @Input() style:{}={};

  ngOnInit(): void {
    console.log(this.outline);
  }

}
