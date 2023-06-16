package com.example.opentalk.service;

import com.example.opentalk.entity.KeyStorage;
import com.example.opentalk.model.KeyModel;
import com.example.opentalk.model.StatusError;
import com.example.opentalk.repository.KeyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


@Service
public class KeyService {

     private PasswordEncoder passwordEncoder;

     private KeyRepository keyRepository;

     public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
          this.passwordEncoder = passwordEncoder;
     }

     public void setKeyRepository(KeyRepository keyRepository) {
          this.keyRepository = keyRepository;
     }

     public StatusError InputKeyToDB(String k) throws StatusError {
          try {
               KeyStorage keyStorage = new KeyStorage();
               keyStorage.setHashKey(passwordEncoder.encode(k));
               keyRepository.deleteAll();
               keyRepository.save(keyStorage);
               return new StatusError(HttpStatus.OK, "oke");
          }catch (Exception e){
               throw new StatusError(HttpStatus.INTERNAL_SERVER_ERROR, "lỗi");
          }
     }

     public StatusError checkKeyToStart(String k) throws StatusError {
          try {
               String hashKey = keyRepository.get();
               if(passwordEncoder.matches(k, hashKey)){
                    KeyModel.key = k;
                    KeyModel.keyBit = new SecretKeySpec(KeyModel.key.getBytes(), "AES");
                    KeyModel.cipher = Cipher.getInstance("AES");
                    return new StatusError(HttpStatus.OK, "oke");
               }
               return new StatusError(HttpStatus.BAD_REQUEST, "Error");
          }catch (Exception e){
               System.out.println(e);
               return new StatusError(HttpStatus.INTERNAL_SERVER_ERROR, "Error");
          }
     }
}
