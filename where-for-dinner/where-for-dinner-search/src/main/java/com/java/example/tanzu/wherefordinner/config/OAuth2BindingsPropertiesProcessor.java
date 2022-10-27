package com.java.example.tanzu.wherefordinner.config;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.bindings.Binding;
import org.springframework.cloud.bindings.Bindings;
import org.springframework.cloud.bindings.boot.BindingsPropertiesProcessor;
import org.springframework.core.env.Environment;

public class OAuth2BindingsPropertiesProcessor implements BindingsPropertiesProcessor
{

    public static final String TYPE = "oauth2";


	@Override
	public void process(Environment environment, Bindings bindings, Map<String, Object> properties) 
	{   
        if (!environment.getProperty("org.springframework.cloud.bindings.boot.oauth2.enable", Boolean.class, true)) 
        {
            return;
        }
        List<Binding> myBindings = bindings.filterBindings(TYPE);
        if (myBindings.size() == 0) 
        {
            return;
        }
        properties.put("spring.security.oauth2.resourceserver.jwt.issuer-uri", myBindings.get(0).getSecret().get("issuer-uri"));
		
	}
}
