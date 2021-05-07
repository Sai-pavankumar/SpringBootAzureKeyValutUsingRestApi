package com.azure.secrets.handler;

import com.azure.secrets.helper.KeyManagementHelper;
import com.azure.secrets.model.AzureSecret;
import com.microsoft.azure.keyvault.KeyVaultClient;
import com.microsoft.azure.keyvault.models.SecretBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


import java.util.*;

@Component
public class KeyManagementHandler {

    private static final Logger logger = LoggerFactory.getLogger(KeyManagementHandler.class);

    @Autowired
    private Environment environment;
    @Autowired
    KeyManagementHelper keyManagementHelper;
    @Autowired
    AzureSecret secret;

    public AzureSecret getAzureSecretKey(String key) {
        logger.info("new Request came to handler method to getAzureSecretKey :" + key);

        String azureSecretVaultUrl = environment.getProperty("azure.secret.vault.url");
        KeyVaultClient vaultClient = keyManagementHelper.getKeyVaultClient();
        SecretBundle secretBundle = vaultClient.getSecret(azureSecretVaultUrl, key);
        if(Objects.nonNull(secretBundle)) {
            secret.setKey(key);
            secret.setValue(secretBundle.value());
        }
        return secret;
    }

    public Map<String, String> createAzureSecretKey(List<AzureSecret> azureSecrets) {

        logger.info("new Request came to handler method for createAzureSecretKey:");
        Map<String, String> map;
        String azureSecretVaultUrl = environment.getProperty("azure.secret.vault.url");
        KeyVaultClient vaultClient = keyManagementHelper.getKeyVaultClient();
        List<SecretBundle> secretBundles = new ArrayList<>();

        azureSecrets.stream().forEach(azureSecret -> {
            vaultClient.setSecret(azureSecretVaultUrl, azureSecret.getKey(), azureSecret.getValue());
            secretBundles.add(vaultClient.getSecret(azureSecretVaultUrl, azureSecret.getKey()));
        });

        return !Objects.equals(secretBundles.size(), 0)? map = Collections.singletonMap("Status", "Success"):Collections.singletonMap("Status", "Failure");

    }
}
