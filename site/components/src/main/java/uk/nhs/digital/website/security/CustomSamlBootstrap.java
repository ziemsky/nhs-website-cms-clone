package uk.nhs.digital.website.security;

import org.opensaml.Configuration;
import org.opensaml.xml.security.BasicSecurityConfiguration;
import org.opensaml.xml.signature.SignatureConstants;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.security.saml.SAMLBootstrap;

public class CustomSamlBootstrap extends SAMLBootstrap {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        super.postProcessBeanFactory(beanFactory);
        final BasicSecurityConfiguration configuration = (BasicSecurityConfiguration) Configuration.getGlobalSecurityConfiguration();
        configuration.registerSignatureAlgorithmURI("RSA", SignatureConstants.ALGO_ID_SIGNATURE_RSA_SHA256);
        configuration.setSignatureReferenceDigestMethod(SignatureConstants.ALGO_ID_DIGEST_SHA256);
    }
}
