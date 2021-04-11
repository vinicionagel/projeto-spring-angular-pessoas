package com.envolti.teste.service;

import com.envolti.teste.builder.PessoaDTOBuilder;
import com.envolti.teste.dto.PessoaDTO;
import com.envolti.teste.entity.Pessoa;
import com.envolti.teste.exception.PessoaAlreadyRegisteredException;
import com.envolti.teste.exception.PessoaNotFoundException;
import com.envolti.teste.mapper.PessoaMapper;
import com.envolti.teste.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    public static final String CPF_VALIDO = "08110629962";
    @Mock
    private PessoaRepository pessoaRepository;

    private PessoaMapper pessoaMapper = PessoaMapper.INSTANCE;

    @InjectMocks
    private PessoaService pessoaService;

    @Test
    void whenPessoaInformedThenItShouldBeCreated() throws PessoaAlreadyRegisteredException {
        // given
        PessoaDTO expectedPessoaDTO = PessoaDTOBuilder.builder().build().toPessoaDTO();
        PessoaDTO createdPessoaWithoutMaskCpf = PessoaDTOBuilder.builder().build().toPessoaDTO();
        createdPessoaWithoutMaskCpf.limparCampoCpf();

        Pessoa expectedSavedPessoa = pessoaMapper.toModel(createdPessoaWithoutMaskCpf);

        // when
        when(pessoaRepository.findByCpf(createdPessoaWithoutMaskCpf.getCpf())).thenReturn(Optional.empty());
        when(pessoaRepository.save(expectedSavedPessoa)).thenReturn(expectedSavedPessoa);

        //then
        PessoaDTO createdPessoaDTO = pessoaService.createPessoa(expectedPessoaDTO);

        assertThat(createdPessoaDTO.getId(), is(equalTo(expectedPessoaDTO.getId())));
        assertThat(createdPessoaDTO.getNome(), is(equalTo(expectedPessoaDTO.getNome())));
    }

    @Test
    void whenPessoaInformedThenItShouldBeCreatedCpfNoMask() throws PessoaAlreadyRegisteredException {
        // given
        PessoaDTO expectedPessoaDTO = PessoaDTOBuilder.builder().build().toPessoaDTO();
        PessoaDTO createdPessoaWithoutMaskCpf = PessoaDTOBuilder.builder().build().toPessoaDTO();
        createdPessoaWithoutMaskCpf.limparCampoCpf();
        Pessoa expectedSavedPessoa = pessoaMapper.toModel(createdPessoaWithoutMaskCpf);

        // when
        when(pessoaRepository.findByCpf(createdPessoaWithoutMaskCpf.getCpf())).thenReturn(Optional.empty());
        when(pessoaRepository.save(expectedSavedPessoa)).thenReturn(expectedSavedPessoa);

        //then
        PessoaDTO createdPessoaDTO = pessoaService.createPessoa(expectedPessoaDTO);

        assertThat(createdPessoaDTO.getId(), is(equalTo(expectedPessoaDTO.getId())));
        assertThat(createdPessoaDTO.getNome(), is(equalTo(expectedPessoaDTO.getNome())));
        assertThat(createdPessoaDTO.getCpf(), is(equalTo(CPF_VALIDO)));
    }

    @Test
    void whenPessoaInformedThenItShouldBeUpdate() throws PessoaAlreadyRegisteredException, PessoaNotFoundException {
        // given
        PessoaDTO expectedPessoaDTO = PessoaDTOBuilder.builder().build().toPessoaDTO();
        PessoaDTO createdPessoaWithoutMaskCpf = PessoaDTOBuilder.builder().build().toPessoaDTO();
        createdPessoaWithoutMaskCpf.limparCampoCpf();
        Pessoa expectedUpdatePessoa = pessoaMapper.toModel(createdPessoaWithoutMaskCpf);

        // when
        when(pessoaRepository.findByCpf(createdPessoaWithoutMaskCpf.getCpf())).thenReturn(Optional.of(expectedUpdatePessoa));
        when(pessoaRepository.findById(1l)).thenReturn(Optional.of(expectedUpdatePessoa));
        when(pessoaRepository.save(expectedUpdatePessoa)).thenReturn(expectedUpdatePessoa);

        //then
        PessoaDTO createdPessoaDTO = pessoaService.update(expectedPessoaDTO, expectedPessoaDTO.getId());

        assertThat(createdPessoaDTO.getId(), is(equalTo(expectedPessoaDTO.getId())));
        assertThat(createdPessoaDTO.getNome(), is(equalTo(expectedPessoaDTO.getNome())));
        assertThat(createdPessoaDTO.getCpf(), is(equalTo(CPF_VALIDO)));
    }

    @Test
    void whenAlreadyRegisteredPessoaInformedThenAnExceptionShouldBeThrown() {
        PessoaDTO expectedPessoaDTO = PessoaDTOBuilder.builder().build().toPessoaDTO();
        Pessoa duplicatedPessoa = pessoaMapper.toModel(expectedPessoaDTO);
        expectedPessoaDTO.limparCampoCpf();
        duplicatedPessoa.setId(2L);

        when(pessoaRepository.findByCpf(expectedPessoaDTO.getCpf())).thenReturn(Optional.of(duplicatedPessoa));

        assertThrows(PessoaAlreadyRegisteredException.class, () -> pessoaService.createPessoa(expectedPessoaDTO));
    }

    @Test
    void whenValidPessoaNameIsGivenThenReturnAPessoa() throws PessoaNotFoundException {
        PessoaDTO expectedFoundPessoaDTO = PessoaDTOBuilder.builder().build().toPessoaDTO();
        Pessoa expectedFoundPessoa = pessoaMapper.toModel(expectedFoundPessoaDTO);

        when(pessoaRepository.findByCpf(expectedFoundPessoa.getNome())).thenReturn(Optional.of(expectedFoundPessoa));

        PessoaDTO foundPessoaDTO = pessoaService.findByCpf(expectedFoundPessoaDTO.getNome());

        assertThat(foundPessoaDTO, is(equalTo(expectedFoundPessoaDTO)));
    }

    @Test
    void whenNotRegisteredPessoaNameIsGivenThenThrowAnException() {
        PessoaDTO expectedFoundPessoaDTO = PessoaDTOBuilder.builder().build().toPessoaDTO();

        when(pessoaRepository.findByCpf(expectedFoundPessoaDTO.getNome())).thenReturn(Optional.empty());

        assertThrows(PessoaNotFoundException.class, () -> pessoaService.findByCpf(expectedFoundPessoaDTO.getNome()));
    }

    @Test
    void whenListPessoaIsCalledThenReturnAListOfPessoas() {
        PessoaDTO expectedFoundPessoaDTO = PessoaDTOBuilder.builder().build().toPessoaDTO();
        Pessoa expectedFoundPessoa = pessoaMapper.toModel(expectedFoundPessoaDTO);

        when(pessoaRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundPessoa));

        List<PessoaDTO> foundListPessoasDTO = pessoaService.listAll();

        assertThat(foundListPessoasDTO, is(not(empty())));
        assertThat(foundListPessoasDTO.get(0), is(equalTo(expectedFoundPessoaDTO)));
    }

    @Test
    void whenListPessoaIsCalledThenReturnAnEmptyListOfPessoa() {
        when(pessoaRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        List<PessoaDTO> foundListPessoasDTO = pessoaService.listAll();

        assertThat(foundListPessoasDTO, is(empty()));
    }

    @Test
    void whenExclusionIsCalledWithValidIdThenAPessoaShouldBeDeleted() throws PessoaNotFoundException {
        PessoaDTO expectedDeletedPessoaDTO = PessoaDTOBuilder.builder().build().toPessoaDTO();
        Pessoa expectedDeletedPessoa = pessoaMapper.toModel(expectedDeletedPessoaDTO);

        when(pessoaRepository.findById(expectedDeletedPessoaDTO.getId())).thenReturn(Optional.of(expectedDeletedPessoa));
        doNothing().when(pessoaRepository).deleteById(expectedDeletedPessoaDTO.getId());

        pessoaService.deleteById(expectedDeletedPessoaDTO.getId());

        verify(pessoaRepository, times(1)).findById(expectedDeletedPessoaDTO.getId());
        verify(pessoaRepository, times(1)).deleteById(expectedDeletedPessoaDTO.getId());
    }
}
