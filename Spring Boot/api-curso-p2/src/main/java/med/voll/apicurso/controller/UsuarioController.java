package med.voll.apicurso.controller;

import jakarta.validation.Valid;
import med.voll.apicurso.model.usuario.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private AuthenticationManager manager;
    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid UserRequest userLogin) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userLogin.login(), userLogin.senha());
        Authentication authentication = manager.authenticate(token);
        return ResponseEntity.ok().build();
    }

}
