package com.kennethcaneda.tienda.service;

import com.kennethcaneda.tienda.entity.Usuario;
import com.kennethcaneda.tienda.enumtypes.UserRole;
import com.kennethcaneda.tienda.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public AuthService(PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado con correo: " + username));

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .roles(usuario.getRol().name())
                .build();
    }

    public boolean register(String nombreUsuario, String apellidoUsuario, String email, String password, Integer edad){
        if (usuarioRepository.findByEmail(email).isPresent()){
            return false;
        }
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombreUsuario(nombreUsuario);
        nuevoUsuario.setApellidoUsuario(apellidoUsuario);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPassword(passwordEncoder.encode(password));
        nuevoUsuario.setEdadUsuario(edad);
        nuevoUsuario.setRol(UserRole.USUARIO);
        usuarioRepository.save(nuevoUsuario);
        return true;
    }
}
