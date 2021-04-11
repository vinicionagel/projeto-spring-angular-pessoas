import { Component, OnInit } from '@angular/core';
import { Pessoa } from '../pessoa';
import { PessoaService } from '../pessoa.service';
import {ToastrService} from 'ngx-toastr';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CustomValidators} from '../form-validator/form-validator.service';

@Component({
  selector: 'app-pessoa',
  templateUrl: './pessoa.component.html',
  styleUrls: ['./pessoa.component.css']
})
export class PessoaComponent implements OnInit {

  pessoas: Pessoa[];
  form: FormGroup;

  constructor(private pessoaService: PessoaService, private toastr: ToastrService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.buildForm();
    this.carregarPessoas();
  }
  private carregarPessoas() {
    this.pessoaService.getPessoas().subscribe( (result) => {
      this.pessoas = result;
    });
  }
  private buildForm(): void {
    this.form = this.formBuilder.group({
      nome: [null, [Validators.required]],
      cpf: ['', [Validators.required, CustomValidators.cnpjOrCpfValidator]],
      dataNascimento: [null, [Validators.required]],
      email: [null, [Validators.required]],
      sexo: [null, [Validators.required]],
      naturalidade: [null, [Validators.required]],
      nacionalidade: [null, [Validators.required]]
    });
  }

  deletePessoa(pessoa: Pessoa) {
    console.log(pessoa);
    this.toastr.success('Sucesso', 'Deletado', {
      timeOut: 3000,
    });
    // this.pessoaService.deletePessoa(pessoa).subscribe(() => {
    //   this.carregarPessoas();
    // });
  }

  submitForm() {
    this.form.markAllAsTouched();
    if (this.form.valid) {
      this.addPessoa();
    }
  }

  private addPessoa() {
    const pessoa = this.form.value;
    this.pessoaService.salvarPessoa(pessoa).subscribe(() => {
        this.carregarPessoas();
        this.form.reset();
        this.toastr.success('Sucesso', 'Criado', {
          timeOut: 3000,
        });
      });
  }
}
