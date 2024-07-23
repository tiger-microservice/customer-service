package com.tiger.customer.configs.securities;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import com.tiger.customer.exceptions.AuthLogicException;
import com.tiger.customer.exceptions.BusinessLogicException;
import com.tiger.customer.exceptions.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
@RequiredArgsConstructor
public class CustomJwtDecoder implements JwtDecoder {


    @Value("${jwt.singer-key}")
    private String secretKey;

    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            // set signer key
            JWSVerifier verifier = new MACVerifier(secretKey.getBytes());

            SignedJWT signedJWT = SignedJWT.parse(token);

            // verify token with singer key
            var verified = signedJWT.verify(verifier);
            if (!verified) {
                throw new AuthLogicException(ErrorCode.UNAUTHENTICATED);
            }

            return new Jwt(token, signedJWT.getJWTClaimsSet().getIssueTime().toInstant(),
                    signedJWT.getJWTClaimsSet().getExpirationTime().toInstant(),
                    signedJWT.getHeader().toJSONObject(),
                    signedJWT.getJWTClaimsSet().getClaims()
            );
        } catch (ParseException e) {
            throw new BusinessLogicException(ErrorCode.UNAUTHENTICATED);
        } catch (JOSEException e) {
            throw new BusinessLogicException(ErrorCode.UNAUTHENTICATED);
        }
    }
}
