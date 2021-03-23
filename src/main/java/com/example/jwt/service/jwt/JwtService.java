package com.example.jwt.service.jwt;

import com.example.jwt.model.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.util.Date;

//import com.sun.org.slf4j.internal.LoggerFactory;
//
//import java.util.logging.Logger;
@Service
public class JwtService {
    private static final String SECRET_KEY= "tu11111";
    private static final Long EXPIRE_TIME = 86400000L;
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class.getName());

    public String generalTokenLogin(Authentication authentication){
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder().setSubject((userPrinciple.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime()+ EXPIRE_TIME*2))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }
    public boolean validateToken(String authToken){
        try{
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
            return true;
        }catch (SignatureException e){
            logger.error("Invalid JWT signature -> Message: {}", e);
        }catch (MalformedJwtException e){
            logger.error("Invalid JWT token -> Message: {}",e);
        }catch (ExpiredJwtException e){
            logger.error("Expired JWT token -> Message: {} ",e);
        }catch (UnsupportedJwtException e){
            logger.error("Unsupported JWT token -> Message: {}",e);
        }catch (IllegalArgumentException e){
            logger.error("Jwt claims string is empty -> Message: {}", e);
        }
        return false;
    }
    public String getUserNameFromJwtToken(String token){
        String username = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
                          .getBody().getSubject();
        return username;
    }

}
