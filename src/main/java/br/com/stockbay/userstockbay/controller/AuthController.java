package br.com.stockbay.userstockbay.controller;

import br.com.stockbay.userstockbay.request.LoginRequest;
import br.com.stockbay.userstockbay.domain.user.Auth;
import br.com.stockbay.userstockbay.infra.segurity.TokenConfigurations;
import br.com.stockbay.userstockbay.repositery.AuthRepositery;
import br.com.stockbay.userstockbay.request.AuthenticationRequest;
import br.com.stockbay.userstockbay.request.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthRepositery repositery;
    @Autowired
   private TokenConfigurations tokenConfigurations;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationRequest data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenConfigurations.generateToken((Auth) auth.getPrincipal());

          return ResponseEntity.ok(new LoginRequest(token));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequest data) {
        if (this.repositery.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
        String encrytedPassword = new BCryptPasswordEncoder().encode(data.password());
        Auth newUser = new Auth(data.login(), encrytedPassword, data.role());
        this.repositery.save(newUser);
        return ResponseEntity.ok().build();

    }
}
