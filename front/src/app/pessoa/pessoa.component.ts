import { Component, OnInit } from '@angular/core';
import { Pessoa } from './pessoa';
import { PessoaService } from './pessoa.service';
import {ToastrService} from 'ngx-toastr';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CustomValidators} from '../form-validator/form-validator.service';
import {ConfirmDialogService} from '../confirm-dialog/confirm-dialog.service';

@Component({
  selector: 'app-pessoa',
  templateUrl: './pessoa.component.html',
  styleUrls: ['./pessoa.component.css']
})
export class PessoaComponent implements OnInit {

  pessoas: Pessoa[];
  form: FormGroup;


  constructor(private pessoaService: PessoaService, private toastr: ToastrService,
              private formBuilder: FormBuilder, private confirmationDialogService: ConfirmDialogService) { }

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
      email: [null, [Validators.email]],
      sexo: [null],
      naturalidade: [null],
      nacionalidade: [null]
    });
  }

  private deletePessoa(pessoa: Pessoa) {
    console.log(pessoa);
    this.pessoaService.deletePessoa(pessoa).subscribe(() => {
      this.carregarPessoas();
      this.toastr.success('Sucesso', 'Deletado', {
        timeOut: 3000,
      });
    });
  }

  public submitForm() {
    this.form.markAllAsTouched();
    if (this.form.valid) {
      if (this.form.value.id) {
        this.updatePessoa();
        return;
      }
      this.addPessoa();
    }
  }

  public abrirConfirmationDialogDeletePessoa(pessoa: Pessoa) {
    this.confirmationDialogService.confirm('Confirmar remoção', `Você realmente deseja excluir ${pessoa.nome} ?`)
      .then((confirmed) => {
        if (confirmed) {
          this.deletePessoa(pessoa);
        }
      });
  }

  private addPessoa() {
    const pessoa = this.form.value;
    this.pessoaService.salvarPessoa(pessoa).subscribe(() => {
        this.carregarPessoas();
        this.form.reset();
        this.toastr.success('Registro criado com sucesso', 'Salvo', {
          timeOut: 3000,
        });
      }, this.errorMessageTrySaveOrUpdatePessoa());
  }

  private errorMessageTrySaveOrUpdatePessoa() {

    return (error) => {
      if (error.error.message) {
        this.toastr.error(error.error.message, 'Error', {
          timeOut: 3000,
        });
      } else {
        this.toastr.error('Cpf já cadastrado', 'Error', {
          timeOut: 3000,
        });
      }
    };
  }

  carregarPessoaAlteracao(pessoa: Pessoa) {
    this.form.setValue(pessoa);
  }

  private updatePessoa() {
    const pessoa = this.form.value;
    this.pessoaService.alterarPessoa(pessoa).subscribe(() => {
      this.carregarPessoas();
      this.form.reset();
      this.toastr.success('Registro alterado com sucesso', 'Alterado', {
        timeOut: 3000,
      });
    }, this.errorMessageTrySaveOrUpdatePessoa());
  }
}
