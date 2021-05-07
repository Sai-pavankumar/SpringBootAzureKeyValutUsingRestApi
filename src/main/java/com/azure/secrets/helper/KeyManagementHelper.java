package com.azure.secrets.helper;

import com.microsoft.azure.AzureEnvironment;
import com.microsoft.azure.credentials.ApplicationTokenCredentials;
import com.microsoft.azure.keyvault.KeyVaultClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class KeyManagementHelper {

    private static final Logger logger = LoggerFactory.getLogger(KeyManagementHelper.class);

    @Autowired
    private Environment environment;

    public KeyVaultClient getKeyVaultClient() {

        logger.info("new Request came to helper method ");
        String azureApplicationClientId= environment.getProperty("azure.application.client.id");
        String azureApplicationTenantId= environment.getProperty("azure.application.tenant.id");
        String azureApplicationClientKey= environment.getProperty("azure.application.client.key");
        ApplicationTokenCredentials credentials=new ApplicationTokenCredentials(azureApplicationClientId,azureApplicationTenantId,azureApplicationClientKey, AzureEnvironment.AZURE);

        return new KeyVaultClient(credentials);
    }
}
