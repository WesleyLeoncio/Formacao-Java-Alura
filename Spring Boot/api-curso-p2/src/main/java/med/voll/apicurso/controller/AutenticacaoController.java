package med.voll.apicurso.controller;

import jakarta.validation.Valid;
import med.voll.apicurso.model.usuario.entity.Usuario;
import med.voll.apicurso.model.usuario.request.UserRequest;
import med.voll.apicurso.model.usuario.response.TokenJwtResponse;
import med.voll.apicurso.service.TokenService;
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
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping()
    public ResponseEntity<TokenJwtResponse> efetuarLogin(@RequestBody @Valid UserRequest userLogin) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userLogin.login(), userLogin.senha());
        Authentication authentication = manager.authenticate(token);
        String tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJwtResponse(tokenJWT));
    }

}
