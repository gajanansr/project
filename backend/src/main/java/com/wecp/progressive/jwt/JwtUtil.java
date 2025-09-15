// package com.wecp.progressive.jwt;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;

// import com.wecp.progressive.repository.UserRepository;

// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.function.Function;

// @Component
// public class JwtUtil {

//     private UserRepository userRepository;

//     @Autowired
//     public JwtUtil(UserRepository userRepository){
//         this.userRepository = userRepository;
//     }

//     private final String secret = "secretKey000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

//     private final int expiration = 86400;

//     public String extractUsername(String token) {
//         return extractClaim(token, Claims::getSubject);
//     }

//     public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//         final Claims claims = extractAllClaims(token);
//         return claimsResolver.apply(claims);
//     }

//     private Claims extractAllClaims(String token) {
//         return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//     }

//     public String generateToken(String username) {
//         Map<String, Object> claims = new HashMap<>();
//         return createToken(claims, username);
//     }

//     private String createToken(Map<String, Object> claims, String subject) {
//         return Jwts.builder().setClaims(claims).setSubject(subject)
//                 .setIssuedAt(new Date(System.currentTimeMillis()))
//                 .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//                 .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
//     }
    
//     public Boolean validateToken(String token, UserDetails userDetails) {
//         final String username = extractUsername(token);
//         return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//     }

//     private Boolean isTokenExpired(String token) {
//         return extractExpiration(token).before(new Date());
//     }

//     private Date extractExpiration(String token) {
//         return extractClaim(token, Claims::getExpiration);
//     }
// }


package com.wecp.progressive.jwt;
 
 
import com.wecp.progressive.entity.User;
import com.wecp.progressive.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
 
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
 
@Component
public class JwtUtil {
 
    private UserRepository userRepository;
 
    @Autowired
    public JwtUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
 
    private final String secret = "secretKey000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
 
    private final int expiration = 86400;
 
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration * 1000);
        User user = userRepository.findByUsername(username);
 
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
 
        // Assign role based on user type
        claims.put("role", user.getRole());
 
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
 
    public Claims extractAllClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
 
    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
 
    public boolean isTokenExpired(String token) {
        Date expirationDate = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expirationDate.before(new Date());
    }
 
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}