package com.devmountain.whereMyPet.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

//Spring is now keeping track of a new Bean called “passwordEncoder”
/*any time you are saving something to the database you should include
the @Transactional annotation which ensures that the transaction that gets
opened with your datasource gets resolved*/