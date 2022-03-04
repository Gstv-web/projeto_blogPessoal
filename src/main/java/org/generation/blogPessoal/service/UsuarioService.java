package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal.DTO.UsuarioCredDTO;
import org.generation.blogPessoal.DTO.UsuarioDTO;
import org.generation.blogPessoal.DTO.UsuarioLoginDTO;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;



@Service
public class UsuarioService {
    
    private UsuarioCredDTO credencialDTO;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private ModelMapper mapper;

    // Criptografa a senha
    private static String SenhaCripto(String senhaCripto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senhaCripto);
    }

    // Cadastra um usuário CASO não conste no banco de dados
    public ResponseEntity<Usuario> cadastrarUsuario(Usuario novoUsuario) {
        Optional<Usuario> optional = repository.findByEmail(novoUsuario.getEmail());

        if (optional.isEmpty()) {
            novoUsuario.setSenha(SenhaCripto(novoUsuario.getSenha()));
            return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(novoUsuario));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERRO: Usuário já existe no banco de dados.");
        }
    } 

    // Valida o email e senha para efetuar um login
    public ResponseEntity<UsuarioCredDTO> logar(@Valid UsuarioLoginDTO dto) {
        return repository.findByEmail(dto.getEmail()).map(resp -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (encoder.matches(dto.getSenha(), resp.getSenha())) {
                credencialDTO = new UsuarioCredDTO(
                            resp.getIdUsuario(),
                            resp.getNome(),
                            geradorTokenBasic(dto.getEmail(), dto.getSenha()),
                            resp.getEmail(),
                            resp.getTipoUsuario(),
                            resp.getFoto());
                return ResponseEntity.status(HttpStatus.OK).body(credencialDTO);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha inválida.");
            }
         }).orElseThrow(() -> {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não existe.");
         });
    }
    
    // gera o token basic para autenticação no header
    private static String geradorTokenBasic(String email, String senha) {
        String auth = email + ":" + senha;
        byte[] authBase64 = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String(authBase64);
    }
    
    public ResponseEntity<List<UsuarioDTO>> usuariosEncontrados(List<Usuario> user) {
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK)
            .body(user.stream()
            .map(resp -> mapper.map(resp, UsuarioDTO.class))
            .collect(Collectors.toList()));
        }
    }

    public ResponseEntity<List<UsuarioDTO>> getUserByName(String nome) {
        List<Usuario> userByName = repository.findAllByNomeContainingIgnoreCase(nome);
        return usuariosEncontrados(userByName);
        
        
    }

    public ResponseEntity<List<Usuario>> getAllUsers() {
        List<Usuario> list = repository.findAll();

        if (list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
    }

    public ResponseEntity<Usuario> findById(Long id) {
        return repository.findById(id)
                            .map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
                            .orElseGet(() -> {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID não encontrado.");
                            });
    }
    
    // Atualiza qualquer dado do usuário. Caso a senha seja alterada, será novamente criptografada
    public ResponseEntity<Usuario> updateUser(@Valid Usuario usuario) {
        Optional<Usuario> optional = repository.findById(usuario.getIdUsuario());

        if (optional.isPresent()) {
            if (usuario.getSenha() != optional.get().getSenha()) {
                usuario.setSenha(usuario.getSenha());
                usuario.setSenha(SenhaCripto(usuario.getSenha()));
            }
        }
        return repository.findById(usuario.getIdUsuario())
                            .map(resp -> ResponseEntity.status(200).body(repository.save(usuario)))
                            .orElseGet(() -> {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID não encontrado");  
                            }); 
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity deleteUser(Long id) {
        Optional<Usuario> optional = repository.findById(id);

        if (optional.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado");
        }
    }    
  

}
