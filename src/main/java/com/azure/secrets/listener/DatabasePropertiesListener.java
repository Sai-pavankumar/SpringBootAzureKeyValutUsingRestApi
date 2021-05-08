package com.azure.secrets.listener;

import com.azure.secrets.controller.KeyManagementResources;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.AzureEnvironment;
import com.microsoft.azure.credentials.ApplicationTokenCredentials;
import com.microsoft.azure.keyvault.KeyVaultClient;
import com.microsoft.azure.keyvault.models.SecretBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

public class DatabasePropertiesListener implements ApplicationListener<ApplicationPreparedEvent> {

    private final static String SPRING_DATASOURCE_USERNAME="spring.datasource.username";
    private final static String SPRING_DATASOURCE_PASSWORD="spring.datasource.password";
    private static final Logger logger = LoggerFactory.getLogger(DatabasePropertiesListener.class);


    /** This is the method which reads the user id pass word from Azure and sets in Spring Context
     *@param args ApplicationPreparedEvent
     *@return void
     */
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent applicationEvent) {

        String azureApplicationClientId = applicationEvent.getApplicationContext().getEnvironment().getProperty("azure.application.client.id");
        String azureApplicationTenantId = applicationEvent.getApplicationContext().getEnvironment().getProperty("azure.application.tenant.id");
        String azureApplicationClientKey = applicationEvent.getApplicationContext().getEnvironment().getProperty("azure.application.client.key");
        String azureSecretVaultUrl = applicationEvent.getApplicationContext().getEnvironment().getProperty("azure.secret.vault.url");
        String azureSecretUserVaultName = applicationEvent.getApplicationContext().getEnvironment().getProperty("azure.secret.user.vault.name");
        String azureSecretPasswordVaultName = applicationEvent.getApplicationContext().getEnvironment().getProperty("azure.secret.password.vault.name");

        ApplicationTokenCredentials credentials = new ApplicationTokenCredentials(azureApplicationClientId,azureApplicationTenantId,azureApplicationClientKey, AzureEnvironment.AZURE);
        KeyVaultClient vaultClient = new KeyVaultClient(credentials);
        SecretBundle userName = vaultClient.getSecret(azureSecretVaultUrl,azureSecretUserVaultName);
        SecretBundle password = vaultClient.getSecret(azureSecretVaultUrl,azureSecretPasswordVaultName);

        String dbUser = userName.value();
        String dbPassword = password.value();

        ConfigurableEnvironment environment = applicationEvent.getApplicationContext().getEnvironment();
        Properties props = new Properties();
        props.put(SPRING_DATASOURCE_USERNAME,dbUser);
        props.put(SPRING_DATASOURCE_PASSWORD,dbPassword);
        environment.getPropertySources().addFirst(new PropertiesPropertySource("myProps",props));


    }
}
