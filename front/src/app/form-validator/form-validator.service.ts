import {any} from 'codelyzer/util/function';

export class CustomValidators {

  static getValidatorErrorMessage(validatorName: string, validatorValue?: any) {
    const config = {
      required: 'Campo obrigatório',
      min: 'Valor Mínimo não atingido',
      max: 'Valor Máximo ultrapassado',
      minlength: `Mínimo de ${validatorValue.requiredLength} caracteres`,
      maxlength: `Máximo de ${validatorValue.requiredLength} caracteres`,
      email: 'E-mail inválido',
      invalidEmail: 'E-mail inválido',
      invalidDate: 'Data inválida',
      bsDate: 'Data inválida',
      mask: `Formato Inválido`,
      invalidCpf: 'CPF inválido',
      invalidCnpj: 'CNPJ inválido',
      passwordNotEqual: 'Senhas não conferem',
      emailNotEqual: 'E-mails não conferem',
      invalidName: 'Informar sobrenome'
    };
    return config[validatorName];
  }

  static emailValidator(control: any) {
    // RFC 2822 compliant regex
    // tslint:disable-next-line: max-line-length
    if (control.value.match(/[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/)) {
      if (/\s/g.test(control.value)) {
        return { invalidEmail: true };
      }
      return null;
    } else {
      return { invalidEmail: true };
    }
  }

  static cpfValidator(control: any) {
    let sum = 0;
    let remaining: number;
    const inputCPF: any = control.value;
    const listInvalidCPF: string[] =
      ['00000000000', '11111111111', '22222222222', '33333333333', '44444444444', '55555555555',
        '66666666666', '77777777777', '88888888888', '99999999999'];

    if (listInvalidCPF.includes(inputCPF)) {
      return { invalidCpf: true };
    }
    for (let i = 1; i <= 9; i++) {
      sum = sum + Number(inputCPF.substring(i - 1, i)) * (11 - i);
    }
    remaining = (sum * 10) % 11;
    if ((remaining === 10) || (remaining === 11)) {
      remaining = 0;
    }
    if (remaining !== Number(inputCPF.substring(9, 10))) {
      return { invalidCpf: true };
    }
    sum = 0;
    for (let i = 1; i <= 10; i++) {
      sum = sum + Number(inputCPF.substring(i - 1, i)) * (12 - i);
    }
    remaining = (sum * 10) % 11;
    if ((remaining === 10) || (remaining === 11)) {
      remaining = 0;
    }
    if (remaining !== Number(inputCPF.substring(10, 11))) {
      return { invalidCpf: true };
    }
    return null;
  }

  static cnpjValidator(control: any) {
    const cnpj = control.value;
    if (!cnpj) {
      return { invalidCnpj: true };
    }
    if (cnpj.length !== 14) {
      return { invalidCnpj: true };
    }
    const listInvalidCnpj: string[] =
      ['00000000000000', '11111111111111', '22222222222222', '33333333333333', '44444444444444', '55555555555555',
        '66666666666666', '77777777777777', '88888888888888', '99999999999999'];

    if (listInvalidCnpj.includes(cnpj)) {
      return { invalidCnpj: true };
    }
    let tamanho = cnpj.length - 2;
    let numeros = cnpj.substring(0, tamanho);
    const digitos = cnpj.substring(tamanho);
    let soma = 0;
    let pos = tamanho - 7;
    for (let index = tamanho; index >= 1; index--) {
      soma += numeros.charAt(tamanho - index) * pos--;
      if (pos < 2) {
        pos = 9;
      }
    }
    let resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado.toString() !== digitos.charAt(0)) {
      return { invalidCnpj: true };
    }
    tamanho = tamanho + 1;
    numeros = cnpj.substring(0, tamanho);
    soma = 0;
    pos = tamanho - 7;

    for (let index = tamanho; index >= 1; index--) {
      soma += numeros.charAt(tamanho - index) * pos--;
      if (pos < 2) {
        pos = 9;
      }
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado.toString() !== digitos.charAt(1)) {
      return { invalidCnpj: true };
    }
    return null;
  }

  static cnpjOrCpfValidator(control: any) {
    const input: any = control.value || '';
    if (input.length === 11) {
      let sum = 0;
      let remaining: number;
      const inputCPF: any = control.value;
      const listInvalidCPF: string[] =
        ['00000000000', '11111111111', '22222222222', '33333333333', '44444444444', '55555555555',
          '66666666666', '77777777777', '88888888888', '99999999999'];

      if (listInvalidCPF.includes(inputCPF)) {
        return { invalidCpf: true };
      }
      for (let i = 1; i <= 9; i++) {
        sum = sum + Number(inputCPF.substring(i - 1, i)) * (11 - i);
      }
      remaining = (sum * 10) % 11;
      if ((remaining === 10) || (remaining === 11)) {
        remaining = 0;
      }
      if (remaining !== Number(inputCPF.substring(9, 10))) {
        return { invalidCpf: true };
      }
      sum = 0;
      for (let i = 1; i <= 10; i++) {
        sum = sum + Number(inputCPF.substring(i - 1, i)) * (12 - i);
      }
      remaining = (sum * 10) % 11;
      if ((remaining === 10) || (remaining === 11)) {
        remaining = 0;
      }
      if (remaining !== Number(inputCPF.substring(10, 11))) {
        return { invalidCpf: true };
      }
      return null;
    }
    if (input.length === 14) {
      const cnpj = control.value;
      if (!cnpj) {
        return { invalidCnpj: true };
      }
      if (cnpj.length !== 14) {
        return { invalidCnpj: true };
      }
      const listInvalidCnpj: string[] =
        ['00000000000000', '11111111111111', '22222222222222', '33333333333333', '44444444444444', '55555555555555',
          '66666666666666', '77777777777777', '88888888888888', '99999999999999'];

      if (listInvalidCnpj.includes(cnpj)) {
        return { invalidCnpj: true };
      }
      let tamanho = cnpj.length - 2;
      let numeros = cnpj.substring(0, tamanho);
      const digitos = cnpj.substring(tamanho);
      let soma = 0;
      let pos = tamanho - 7;
      for (let index = tamanho; index >= 1; index--) {
        soma += numeros.charAt(tamanho - index) * pos--;
        if (pos < 2) {
          pos = 9;
        }
      }
      let resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
      if (resultado.toString() !== digitos.charAt(0)) {
        return { invalidCnpj: true };
      }
      tamanho = tamanho + 1;
      numeros = cnpj.substring(0, tamanho);
      soma = 0;
      pos = tamanho - 7;

      for (let index = tamanho; index >= 1; index--) {
        soma += numeros.charAt(tamanho - index) * pos--;
        if (pos < 2) {
          pos = 9;
        }
      }
      resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
      if (resultado.toString() !== digitos.charAt(1)) {
        return { invalidCnpj: true };
      }
      return null;
    }
  }

  static nameValidator(control: any) {
    const pattern = /\s/;
    if (control.value.match(pattern)) {
      return null;
    } else {
      return { invalidName: true };
    }
  }

}
