package com.springboot.speedscape;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserEntity> getUsers() {
        return repository.findAll();
    }

    public UserEntity create(NewUser newUser) {
        return repository.saveAndFlush(new UserEntity(newUser.username(), newUser.name(), newUser.surname(), newUser.email(), newUser.password()));
    }

    public List<UserEntity> search(UserSearch userSearch) {
        if(StringUtils.hasText(userSearch.username())
        && StringUtils.hasText(userSearch.password())) {
            return repository.findByUsernameContainsOrPasswordContains ( //
                userSearch.username(), userSearch.password());
        }
        
        if (StringUtils.hasText(userSearch.username())) {
            return repository.findByUsernameContains(userSearch.username());
        }
      
        if (StringUtils.hasText(userSearch.password())) {
            return repository.findByPasswordContains(userSearch.password());
        }
      
        return Collections.emptyList();
    }

    // public boolean deleteUser(UserDelete userDelete) {
    //     // Obtener todos los usuarios de la base de datos
    //     List<UserEntity> allUsers = repository.findAll();
    
    //     // Iterar sobre la lista de usuarios y buscar el que coincida con el nombre de usuario proporcionado
    //     for (UserEntity user : allUsers) {
    //         if (user.getUsername().equals(userDelete.username()) && user.getPassword().equals(userDelete.password())) {
    //             // Si se encuentra el usuario y las contraseñas coinciden, eliminarlo
    //             repository.delete(user);
    //             return true; // Indica que el usuario fue eliminado con éxito
    //         }
    //     }
    
    //     return false; // Indica que el usuario no se encontró o las credenciales son incorrectas
    // }
    
}