package azure.secrets.controller;

import com.azure.secrets.handler.KeyManagementHandler;
import com.azure.secrets.model.AzureSecret;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
@ComponentScan("com/azure/secrets")
public class KeyManagementResources extends SpringBootServletInitializer {

    private static final Logger logger =LoggerFactory.getLogger(azure.secrets.controller.KeyManagementResources.class);

    @Autowired
    KeyManagementHandler keyManagementHandler;

    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path","/");
        SpringApplication.run(azure.secrets.controller.KeyManagementResources.class,args);
    }

    /** This method which takes the get Request with key as param and returns json string with key and value
     *
     * @param key
     * @return String key and value
     */
  @GetMapping("/secret")
    public AzureSecret getAzureSecretKey(@RequestParam("key") String key){
        logger.info("Request came to getAzureKey with key as parameter.."+key);
        return keyManagementHandler.getAzureSecretKey(key);
  }

    /** This method which takes the post Request with request body as key and value json string
     *
     * @param key,value
     * @return Success
     */
    @PostMapping("/secret/key")
    public Map<String,String> createAzureSecretKey(@RequestBody List<AzureSecret> secretKeys){
        logger.info("Request came to createAzureSecretKey with key and value .."+secretKeys);
        return keyManagementHandler.createAzureSecretKey(secretKeys);
    }
}
