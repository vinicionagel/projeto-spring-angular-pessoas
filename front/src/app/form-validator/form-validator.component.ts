import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';
import { CustomValidators } from './form-validator.service';

@Component({
  selector: 'app-form-validator',
  templateUrl: './form-validator.component.html',
  styleUrls: ['./form-validator.component.scss']
})
export class FormValidatorComponent {

  @Input() control: FormControl;

  constructor() { }

  get errorMessage() {
    for (const propertyName in this.control.errors) {
      if (this.control.errors.hasOwnProperty(propertyName) &&
        (this.control.touched)) {
        return CustomValidators.getValidatorErrorMessage(
          propertyName, this.control.errors[propertyName]
        );
      }
    }
    return null;
  }

}
