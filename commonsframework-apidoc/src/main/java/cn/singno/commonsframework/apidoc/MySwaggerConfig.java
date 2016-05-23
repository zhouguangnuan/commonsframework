package cn.singno.commonsframework.apidoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.xml.stream.events.Characters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.google.common.base.Charsets;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@Configuration
@EnableSwagger
public class MySwaggerConfig
{

    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig)
    {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean
    public SwaggerSpringMvcPlugin customImplementation()
    {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo()).includePatterns(".*?");
    }

    private ApiInfo apiInfo()
    {
    	String title = null;
    	String description = null;
    	String termsOfServiceUrl = null;
    	String contact = null;
    	String license = null;
    	String licenseUrl = null; 
    	
        try {
        	ResourceLoader resourceLoader = new DefaultResourceLoader();
        	Properties props = new Properties();
        	Resource resource = resourceLoader.getResource("classpath:swagger.properties");
        	BufferedReader bf = new BufferedReader(new InputStreamReader(resource.getInputStream(), Charsets.UTF_8));  
			props.load(bf);
			
			title = props.getProperty("title");
			description = props.getProperty("description");
			termsOfServiceUrl = props.getProperty("termsOfServiceUrl");
			contact = props.getProperty("contact");
			license = props.getProperty("license");
			licenseUrl = props.getProperty("licenseUrl");
		} catch (IOException e) {
			e.printStackTrace();
		}

        ApiInfo apiInfo = new ApiInfo(title, description, termsOfServiceUrl, contact, license, licenseUrl);
        return apiInfo;
    }
}