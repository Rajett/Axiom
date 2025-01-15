package com.dist.interview.axiomauth.serviceapi;

import org.springframework.stereotype.Service;

@Service
public interface AxiomModule {
     void saveUserToAxiom(String email, String password) ;

}
