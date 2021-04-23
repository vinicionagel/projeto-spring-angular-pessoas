import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Pessoa } from './pessoa';

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  constructor(private http: HttpClient) { }

  getPessoas() {
    return this.http.get<Pessoa[]>('http://localhost:8080/api/v1/pessoas');
  }

  deletePessoa(pessoa: Pessoa) {
    return this.http.delete(`http://localhost:8080/api/v1/pessoas/${pessoa.id}`);
  }

  salvarPessoa(pessoa: Pessoa) {
    return this.http.post(`http://localhost:8080/api/v1/pessoas`, pessoa);
  }

  alterarPessoa(pessoa: Pessoa) {
    return this.http.put(`http://localhost:8080/api/v1/pessoas/${pessoa.id}`, pessoa);
  }
}
