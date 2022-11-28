package br.com.seg.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.seg.dto.UserDTO;
import br.com.seg.entity.Fornecedor;
import br.com.seg.entity.User;
import br.com.seg.repository.FornecedorRepository;
import br.com.seg.repository.UserRepository;
import br.com.seg.service.exception.ResourceNotFoundException;
import br.com.seg.service.exception.ObjetoNulo;
import br.com.seg.service.exception.ResourceNotFound;
import br.com.seg.utils.ValidaDocumento;
import br.com.seg.utils.Validadores;

@Service
public class UserService{
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public FornecedorRepository fornRepository;
	
	//private Logger logger = Logger.getLogger(UserService.class.getName());
	
	@Transactional(readOnly = true) //Avisa que não precisar lockar registros
	public ResponseEntity<UserDTO> findById(@Valid String id) {
		User usuario = userRepository.findById(id)
				.orElseThrow( ()-> new ResourceNotFoundException("Usuário não encontrado com este ID"));
		
		//Transforma o dados do repository para devolver usando o DTO
		UserDTO dto = new UserDTO(usuario);
		return ResponseEntity.ok(dto);
	}


	@Transactional()
	public List<UserDTO> findAll() {
		List<User> usuarios = userRepository.findAll();
		return usuarios.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
	}

	@Transactional
	public ResponseEntity<UserDTO> create(UserDTO usuario) {
	//	User newUser = usuario; //SEM DTO
	//	validaUsuario(usuario,newUser,"INS");		
//		return new ResponseEntity<User>(HttpStatus.OK);
		
		long id  = usuario.getForId();
		Fornecedor forn = fornRepository.findById(id)
				.orElseThrow( () -> 
				new ResourceNotFoundException("Fornecedor não encontrado com o código: " + id  + ". Impossível incluir login"));
		
		User newUser = new User();
		newUser.setFornecedor(forn); //Recupero pelo ID e retorno tudo.
		newUser.setUserId(usuario.getUserId());
		newUser.setUserName(usuario.getUserName());
		newUser.setUsermail(usuario.getUsermail());
		newUser.setStatus(usuario.isStatus());
		newUser.setCodVendedor(usuario.getCodVendedor());
		newUser.setLoja(usuario.getLoja());
		newUser.setCpfUsuario(usuario.getCpfUsuario());	
		
		validaUsuario(usuario, newUser, "INS");
		
		userRepository.save(newUser);
		
		return new ResponseEntity<UserDTO>(HttpStatus.OK);
	}
	
	@Transactional
	public ResponseEntity<UserDTO> update(UserDTO usuario) {
		String id = usuario.getUserId();
		
		if (id ==null ) {
			throw new ResourceNotFoundException("Obrigatório informar o ID para localizar o registro");
		} //Incluído fora para tratar o erro 500
		
		User newUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado para atualizar"));

		validaUsuario(usuario,newUser,"UPD");//Neste caso validar tudo, estrutura obrigatórias
		
		userRepository.save(newUser);
		//Transforma o dados do repository para devolver usando o DTO
		UserDTO dto = new UserDTO(newUser);		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<Object> delete (String Id){
		User usuario = userRepository.findById(Id)
				.orElseThrow(()-> new ResourceNotFoundException("Registro não encontrado para atualizar"));
		userRepository.delete(usuario);
		return ResponseEntity.noContent().build();

	}

	//********************************Rotina de validação***************************
	
	private void validaUsuario(UserDTO usuario, User newUser, String Mode) {

		String id 	 = usuario.getUserId();

		if (Mode == "INS" && userRepository.findById(id).isPresent()) {
			throw new ResourceNotFound("ID já existente na base de dados, altere o ID e reenvie");
		}
			
		// Valida CPF
		String nCpf = usuario.getCpfUsuario();
		
		if (Mode == "UPD" && nCpf == null) {
			newUser.setCpfUsuario(newUser.getCpfUsuario());
		} else {
			if (nCpf != null) {
				ValidaDocumento cpf = new ValidaDocumento(nCpf, "CPF");

				if (cpf.isCPF() == false) {
					throw new ResourceNotFound("O CPF informado é inválido");
				} else {
					
					if (ValidaDocumento.LimpaDoc(nCpf, "CPF").length() > 11) {
						throw new ResourceNotFound("O CPF deve conter apenas 11 caracteres. Foram informados: "
								+ nCpf.length() + " caracteres");
					}
				}
			} else {
				throw new ResourceNotFound("O CPF informado é inválido ou vazio");
			}
			newUser.setCpfUsuario(ValidaDocumento.LimpaDoc(usuario.getCpfUsuario(),"CPF"));
		}

		// validar e-mail
		List<User> userMails = userRepository.findByuserMail(usuario.getUsermail());
		
		for (User mails : userMails) {
			if (mails.getUsermail().equals( usuario.getUsermail()) && !mails.getUserId().equals(usuario.getUserId()) ) {
				throw new ResourceNotFound("O e-mail informado pertence ao outro usuário: " + mails.getUserId());
			}
		}
		
		String email = usuario.getUsermail();
		if (Mode == "UPD" && email == null) {
			newUser.setUsermail(newUser.getUsermail());
		} else {
			if (email == null) {
				throw new ObjetoNulo("O e-mail não pode ser nulo");
			} else {
				if (!usuario.getUsermail().matches(Validadores.MAIL)) {
					throw new ResourceNotFoundException("O e-mail informado é inválido");
				} else {
					if (usuario.getUsermail().length() > 64) {
						throw new ResourceNotFound("E-mail informado superior a 64 caracteres. Você informou: "
								+ usuario.getUsermail().length() + " caracteres");
					} else {
						newUser.setUsermail(email);
					}
				}
			}

		}
		
		
		List<User> userts = userRepository.findByCodVendedor(usuario.getCodVendedor());
		
		for (User vendedor: userts) {
			if (vendedor.getCodVendedor() == usuario.getCodVendedor()  && !vendedor.getUserId().equals(usuario.getUserId()) ) {
				throw new ResourceNotFoundException("Código do vendedor atribuido a outro login: " + vendedor.getUserId());
			} 
		}
		
		newUser.setCodVendedor(usuario.getCodVendedor());			

		
		//Código da Loja
		//Valida o informado versus o gravado e sepre tenta recuperar um valor <> 0.
		int lojaA = newUser.getLoja(); //o recuperado
		int lojaB = usuario.getLoja(); //o informado

		int loja = usuario.getLoja() == 0 ? lojaA : lojaB;

		if (loja == 0) {
			throw new ResourceNotFoundException("Usuário não pode ser incluído sem informar a loja");
		} else {
			if (lojaA != lojaB && lojaB == 0) {
				newUser.setLoja(lojaA);
			} else {
				newUser.setLoja(lojaB);
			}
		}
		
		// Nome
		//Valida se digitou apenas letras e remove os espaços em branco para gravar correto
		String nome = usuario.getUserName();
		if (Mode == "UPD" && nome == null) {
			newUser.setUserName(newUser.getUserName());
		} else {
			if (nome.isBlank() || nome.isEmpty()) {
				throw new ObjetoNulo("O nome do usuário é obrigatório");
			} else {
				if (nome != null && !nome.matches(Validadores.NOME)) {
					throw new ResourceNotFoundException("O nome deve possuir apenas letras de A-Z");
				} else {
					newUser.setUserName(nome.replaceAll("\\s+"," ")); 
				}
			}
		}
	}
}



//if (usuarios.isEmpty() || usuarios == null) {
//	 throw new ResourceNotFoundException("Não existem registros");
//}
//	var retorno = new ResponseEntity<List<UserDTO>>(HttpStatus.OK);

//for (User usuario : usuarios) {
//	logger.info("retorno:"  + usuario.getUserId());
//}


//return new ResponseEntity<List<User>>(usuarios, HttpStatus.OK);