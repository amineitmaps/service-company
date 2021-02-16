package com.example.servicecompany;


import com.example.servicecompany.security.ProjectProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.stream.Stream;
@EnableDiscoveryClient //on dit au micro-service qd tu demarre publie ta reference ds l'annuaire(sans cette annotation, le service d'enregistrement ne peut detecter aucune instance de ce micro-service:il a trouver zero instance)
@EnableConfigurationProperties({ProjectProperties.class})
@SpringBootApplication
@ComponentScan("com.example.servicecompany")
public class ServiceCompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCompanyApplication.class, args);
    }

    /*//Autre méthode au liey d'implementer CommandeLineRunner et de refenir la méthode run
    @Bean //tous ce qui déclarer bean ce sont des methodes qui vont demarrer au démarrage
    CommandLineRunner start(CompanyRepository companyRepository) { //passer en parametre CompanyRepositoryest equivalent à autowired
         return args -> {
             Stream.of("A","B","C").forEach(cn->{
                 companyRepository.save(new Company(null,cn,100+Math.random()*900));
             });
             companyRepository.findAll().forEach(System.out::println); // <=>companyRepository.findAll().forEach(s->{Sytem.out.println(s.toString());forEach par defaut pour chaque methode utilise la méthode toString
         };
    }*/

}
