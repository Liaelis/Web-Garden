package com.elisrs.garden;

import com.elisrs.enty.Permissao;
import com.elisrs.enty.Usuario;
import com.elisrs.repository.UsuarioRepository;
import com.elisrs.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.Collection;
import java.util.Optional;

@Component
public class ImplementsUserDatailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       Optional<Usuario> userOp = usuarioRepository.findUsuarioByEmail(s);
       return userOp.orElseThrow(() -> new UsernameNotFoundException("Usuario nao existe"));
    }

}
