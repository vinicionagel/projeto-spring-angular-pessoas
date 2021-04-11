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
      id: [null],
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
    this.pessoaService.deletePessoa(pessoa).subscribe(() => {
      this.carregarPessoas();
      this.toastr.success('Sucesso', 'Deletado', {
        timeOut: 3000,
      });
    });
  }

  submitForm() {
    this.form.markAllAsTouched();
    if (this.form.valid) {
      if (this.form.value.id) {
        this.updatePessoa();
        return;
      }
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
      }, () => {
      this.toastr.error('Erro', 'ao salvar', {
        timeOut: 3000,
      });
    });
  }

  carregarPessoaAlteracao(pessoa: Pessoa) {
    this.form.setValue(pessoa);
  }

  private updatePessoa() {
    const pessoa = this.form.value;
    this.pessoaService.alterarPessoa(pessoa).subscribe(() => {
      this.carregarPessoas();
      this.form.reset();
      this.toastr.success('Sucesso', 'Alterado', {
        timeOut: 3000,
      });
    }, () => {
      this.toastr.error('Sucesso', 'ao salvar', {
        timeOut: 3000,
      });
    });
  }
}
