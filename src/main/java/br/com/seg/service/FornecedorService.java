package br.com.seg.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.seg.entity.Fornecedor;
import br.com.seg.repository.FornecedorRepository;
import br.com.seg.service.exception.ResourceNotFoundException;
import br.com.seg.service.exception.ResourceNotFound;
import br.com.seg.utils.ValidaDocumento;
import br.com.seg.utils.Validadores;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedor_dados;

	//Localiza registro por ID
	public ResponseEntity<Fornecedor> findbyId(long id) {
		Fornecedor myForn = fornecedor_dados.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado para este ID"));
		return ResponseEntity.ok(myForn);

	}

	public ResponseEntity<List<Fornecedor>> findAll() {		
		List<Fornecedor> result = fornecedor_dados.findAll();

		if (result.isEmpty() || result == null) 
			throw new ResourceNotFoundException("Não foram localizados registros"); 

		return new ResponseEntity<List<Fornecedor>>(result, HttpStatus.OK);
	}

	@Transactional
	public Fornecedor create(Fornecedor myForn) throws Exception{
		ValidaFornecedor(myForn,myForn,'I');

		Fornecedor result = fornecedor_dados.save(myForn);
		return result;
	}

	@Transactional
	public ResponseEntity<Fornecedor> update(Fornecedor myForn)  throws Exception {
		long id  = myForn.getForId();
		Fornecedor myForns = fornecedor_dados.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("O registro não foi encontrado para atualização"));

		ValidaFornecedor(myForn,myForns,'U');

		fornecedor_dados.save(myForns);

		return new ResponseEntity<>(myForns, HttpStatus.OK);

	}

	@Transactional
	public ResponseEntity<Fornecedor> updateParcial(@Valid Fornecedor myForn) {
		long id  = myForn.getForId();
		Fornecedor myForns = fornecedor_dados.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foram localizados registros para atualizar"));
		ValidaFornecedor(myForn,myForns,'P');
		fornecedor_dados.save(myForns);

		return new ResponseEntity<>(myForns, HttpStatus.OK);
		
	}	

	@Transactional
	public void delete(@Valid long ld) {
		var entity = fornecedor_dados.findById(ld)
				.orElseThrow(() -> new ResourceNotFoundException("Não foram localizados registros para exclusão"));
		fornecedor_dados.delete((Fornecedor) entity);
	}

	private void ValidaFornecedor(Fornecedor myForn,Fornecedor myForns, char mode) {

		//Valida CNPJ
		if (mode == 'P' && myForn.getForCnpj() == null) {
			myForns.setForCnpj(myForns.getForCnpj());
		} else {
			if (myForn.getForCnpj() != null && !myForn.getForCnpj().isBlank()) {
				ValidaDocumento cnpj = new ValidaDocumento(myForn.getForCnpj(),"CNPJ");

				if (cnpj.isCNPJ() == false) {
					throw new ResourceNotFound("O CNPJ informado é inválido");
				} else {
					if(myForn.getForCnpj().length() > 14) {
						throw new ResourceNotFound("O CNPJ deve conter 14 caracteres. Você digitou: " + myForn.getForCnpj().length());
					} else  {
						myForns.setForCnpj(myForn.getForCnpj());
					}					
				}
			} else  {
				throw new ResourceNotFound("O CNPJ informado é inválido ou vazio");	
			}

		}//Valida o CNPJ, obtigatório no INS e opcional no update


		//Valida Nome do Fornecedor
		if(mode == 'P' && myForn.getNameFor() == null) {
			myForns.setNameFor(myForns.getNameFor());
		} else {
			if (myForn.getNameFor() != null) {
				if (myForn.getNameFor().isBlank()) {
					String mensagem  = "O nome do Fornecedor está em branco" +( mode =='P'? ", informe o nome ou omita o campo" : ".");
					throw new ResourceNotFound(mensagem);
				} else {
					if (myForn.getNameFor().length() > 60) {
						throw new ResourceNotFound("O nome do fornecedor deve conter no máximo 60 caracteres, você informou: " + myForn.getNameFor().length());
					}
					myForns.setNameFor(myForn.getNameFor());
				}
			} else  {
				throw new ResourceNotFound("O nome do Fornecedor não pode ser nulo");
			}
		}


		//Valida Telefone	
		if (mode == 'P' && myForn.getForTelefone() == null)	{
			myForns.setForTelefone(myForns.getForTelefone());
		} else {
			if (myForn.getForTelefone() != null ) {
				if (myForn.getForTelefone().isBlank()) {
					throw new ResourceNotFound("Número de telefone não informado");
				} else {
					if(!myForn.getForTelefone().matches(Validadores.CELULAR)) {
						throw new ResourceNotFound("Problemas no preenchimento do telefone formato: 55DDNNNNNNNNN");	
					} else {
						myForns.setForTelefone(myForn.getForTelefone());
					}
				}		
			}

		}

		//Valida e-mail	
		if (mode == 'P' && myForn.getForEmail() == null) {
			myForns.setForEmail(myForns.getForEmail());
		} else {
			if (myForn.getForEmail() != null) {
				if(myForn.getForEmail().isBlank())  {
					throw new ResourceNotFound("O e-mail está em branco");
				} else {
					if (!myForn.getForEmail().matches(Validadores.MAIL)) {
						throw new ResourceNotFound("O e-mail é inválido");
					} else  {
						if (myForn.getForEmail().length() > 255) {
							throw new ResourceNotFound("O e-mail informado é maior que 255 caracteres. Você informou: " + myForn.getForEmail().length());
						} else  {
							myForns.setForEmail(myForn.getForEmail());
						}
						
					}
				}
			}
		}		
	
	}
}





