package com.store.app;

import com.store.app.database.entity.AuthorityEntity;
import com.store.app.database.entity.RoleEntity;
import com.store.app.database.entity.UserEntity;
import com.store.app.database.repository.AuthorityRepository;
import com.store.app.database.repository.RoleRepository;
import com.store.app.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

@Component
public class InitialUserSetup {

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {

        RoleEntity roleAdmin = createRole("ROLE_ADMIN", new ArrayList<>());

        if (roleAdmin == null) return;

        if (userRepository.findByEmail("test@admin.com") == null) {
            UserEntity adminUser = new UserEntity();
            adminUser.setFirstName("Admin");
            adminUser.setLastName("Admin");
            adminUser.setEmail("test@admin.com");
            adminUser.setPublicUserId(UUID.randomUUID().toString());
            adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("123"));
            adminUser.setRoles(Arrays.asList(roleAdmin));
            adminUser.setAddress("Admin");
            adminUser.setCity("Admin");
            adminUser.setTelephone("999999999");
            userRepository.save(adminUser);
        }
    }

    private AuthorityEntity createAuthority(String name){
        AuthorityEntity authority = authorityRepository.findByName(name);
        if(authority==null){
            authority = new AuthorityEntity(name);
            authorityRepository.save(authority);
        }
        return authority;
    }

    private RoleEntity createRole(String name,
                                  Collection<AuthorityEntity> authorities){
        RoleEntity role = roleRepository.findByName(name);
        if(role == null){
            role = new RoleEntity(name);
            role.setAuthorities(authorities);
            roleRepository.save(role);
        }
        return role;
    }
}
