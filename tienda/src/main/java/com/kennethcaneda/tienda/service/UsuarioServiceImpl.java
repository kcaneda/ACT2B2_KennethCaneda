    package com.kennethcaneda.tienda.service;

    import com.kennethcaneda.tienda.entity.Usuario;
    import com.kennethcaneda.tienda.exception.ResourceNotFoundException;
    import com.kennethcaneda.tienda.repository.UsuarioRepository;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    public class UsuarioServiceImpl implements UsuarioService{
        private final UsuarioRepository usuarioRepository;
        private final PasswordEncoder passwordEncoder;

        public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
            this.usuarioRepository = usuarioRepository;
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        public List<Usuario> listar() {
            return usuarioRepository.findAll();
        }

        @Override
        public Usuario crear(Usuario usuario) {
            usuario.setIdUsuario(null);
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            return usuarioRepository.save(usuario);
        }

        @Override
        public Usuario actualizar(Integer id, Usuario usuario) {
            Usuario usuarioExistente = buscarPorId(id);
            usuarioExistente.setNombreUsuario(usuario.getNombreUsuario());
            usuarioExistente.setApellidoUsuario(usuario.getApellidoUsuario());
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioExistente.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuarioExistente.setEdadUsuario(usuario.getEdadUsuario());
            usuarioExistente.setRol(usuario.getRol());
            return usuarioRepository.save(usuarioExistente);
        }

        @Override
        public Usuario buscarPorId(Integer id) {
                return usuarioRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario con ID, no encoontrado:" + id));
        }

        @Override
        public void eliminar(Integer id) {
            if(!usuarioRepository.existsById(id)){
                throw new ResourceNotFoundException("Usuario no encontrado con Id" + id);
            }
            usuarioRepository.deleteById(id);
        }
    }
