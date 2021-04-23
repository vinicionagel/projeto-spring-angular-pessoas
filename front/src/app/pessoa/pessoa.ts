export class Pessoa {

    id: number;
    nome: string;
    email: string;
    sexo: string;
    dataNascimento: string;
    naturalidade: string;
    cpf: string;
    nacionalidade: string;

  constructor(id: number, nome: string, email: string, sexo: string,
              dataNascimento: string, naturalidade: string, cpf: string, nacionalidade: string) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.sexo = sexo;
    this.dataNascimento = dataNascimento;
    this.naturalidade = naturalidade;
    this.cpf = cpf;
    this.nacionalidade = nacionalidade;
  }
}
