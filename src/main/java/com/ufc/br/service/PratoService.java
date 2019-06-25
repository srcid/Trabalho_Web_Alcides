package com.ufc.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ufc.br.model.Prato;
import com.ufc.br.repository.PratoRepository;
import com.ufc.br.util.ImagemFileUtils;

@Service
public class PratoService {
	@Autowired
	private PratoRepository pratoRepository;
	
	public void salvar(Prato prato, MultipartFile imagem) {
		pratoRepository.save(prato);
		String caminho = "images/" + prato.getCodigo() + ".png";
		ImagemFileUtils.salvarImagem(caminho, imagem);
	}
	
	public void delete(Long id) {
		Prato prato = pratoRepository.getOne(id);
		String caminho = "images/" + prato.getCodigo() + ".png";
		ImagemFileUtils.deleteImagem(caminho);
		pratoRepository.deleteById(id);
	}
	
	public Prato buscarPorId(Long id) {
		return pratoRepository.getOne(id);
	}
	
	public List<Prato> listarPratos() {
		return pratoRepository.findAll();
	}
}
