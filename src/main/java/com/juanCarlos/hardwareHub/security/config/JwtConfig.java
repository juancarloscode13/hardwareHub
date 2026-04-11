package com.juanCarlos.hardwareHub.security.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class JwtConfig {

    /**
     * Genera y devuelve un par de claves RSA (pública y privada) que se usarán
     * para firmar y verificar los JWTs.
     * @return KeyPair RSA 2048 bits
     */
    @Bean
    public KeyPair keypair(){
        try{
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Crea una fuente JWK (JSON Web Key) a partir del KeyPair proporcionado.
     * Esta fuente se utiliza por el encoder para obtener las claves y crear
     * tokens JWT firmados.
     * @param keyPair par de claves RSA
     * @return JWKSource que contiene la JWK construida
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource(KeyPair keyPair){
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        JWK jwk = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID("jwt-key-id")
                .build();

        JWKSet jwkSet = new JWKSet(jwk);
        return new ImmutableJWKSet<>(jwkSet);
    }

    /**
     * Provee un `JwtEncoder` que firma los JWTs usando la fuente JWK.
     * @param jwkSource fuente de claves JWK
     * @return JwtEncoder para generar tokens
     */
    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource){
        return new NimbusJwtEncoder(jwkSource);
    }

    /**
     * Provee un `JwtDecoder` que valida JWTs usando la clave pública RSA.
     * @param keyPair par de claves RSA (se usa la pública)
     * @return JwtDecoder para validar tokens entrantes
     */
    @Bean
    public JwtDecoder jwtDecoder(KeyPair keyPair){
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }
}
