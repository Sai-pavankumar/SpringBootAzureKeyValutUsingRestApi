package com.azure.secrets.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AzureConfig {

    /** This is the property for Azure storage account name */

    @Value("${azure.storage.account.name}")
    private String azureStorageAccountName;

    /** This is the property for Azure storage account key */

    @Value("${azure.storage.account.key}")
    private String azureStorageAccountKey;

    /** This is the property for Azure storage Datain Container name */

    @Value("${azure.storage.datain.container}")
    private String azureStorageDatainContainer;

    /** This is the property for Azure storage Datain reference folder name */

    @Value("${azure.storage.datain.reference.folder}")
    private String azureStorageDatainReferenceFolder;

    /** This is the property for Azure application client id */

    @Value("${azure.application.client.id}")
    private String azureApplicationClientId;

    /** This is the property for Azure application tenant id */

    @Value("${azure.application.tenant.id}")
    private String azureApplicationTenantId;

    /** This is the property for Azure application client key */

    @Value("${azure.application.client.key}")
    private String azureApplicationClientKey;

    /**
     * This is the bean which helps to read Azure storage account name
     * @param args
     * @return Azure Storage account name
     */
    @Bean(name="azureStorageAccountName")
    public String getAzureStorageAccountName(){
        return  azureStorageAccountName;
    }
    /**
     * This is the bean which helps to read Azure storage Account key
     * @param args
     * @return Azure Storage account key
     */
    @Bean(name="azureStorageAccountKey")
    public String getAzureStorageAccountKey(){
        return  azureStorageAccountKey;
    }

    /**
     * This is the bean which helps to read Azure storage Datain Container
     * @param args
     * @return Azure Storage Datain Container name
     */
    @Bean(name="azureStorageDatainContainer")
    public String getAzureStorageDatainContainer(){
        return  azureStorageDatainContainer;
    }

    /**
     * This is the bean which helps to read Azure storage Datain Reference Folder
     * @param args
     * @return Azure Storage Datain Reference folder name
     */
    @Bean(name="azureStorageDatainReferenceFolder")
    public String getAzureStorageDatainReferenceFolder(){
        return  azureStorageDatainReferenceFolder;
    }

    /**
     * This is the bean which helps to read Azure Application ClientId
     * @param args
     * @return Azure Application ClientId
     */
    @Bean(name="azureApplicationClientId")
    public String getAzureApplicationClientId(){
        return  azureApplicationClientId;
    }

    /**
     * This is the bean which helps to read Azure Application TenantId
     * @param args
     * @return Azure Application TenantId
     */
    @Bean(name="azureApplicationTenantId")
    public String getAzureApplicationTenantId(){
        return  azureApplicationTenantId;
    }


    /**
     * This is the bean which helps to read Azure Client key
     * @param args
     * @return Azure Application Client key
     */
    @Bean(name="azureApplicationClientKey")
    public String getAzureApplicationClientKey(){
        return  azureApplicationClientKey;
    }
}
