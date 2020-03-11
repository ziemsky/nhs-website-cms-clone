package uk.nhs.digital.website.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;

import java.util.Collection;
import java.util.Collections;

public class HippoSamlUserDetailsService implements SAMLUserDetailsService {

    public static final String DUMMY_PASSWORD = "dummy_password";

    @Override
    public Object loadUserBySAML(SAMLCredential samlCredential) throws UsernameNotFoundException {
        final String username = samlCredential.getNameID().getValue();
        final Collection<GrantedAuthority> authorities = Collections.emptyList();

        return new User(username, DUMMY_PASSWORD, authorities);
    }
}
