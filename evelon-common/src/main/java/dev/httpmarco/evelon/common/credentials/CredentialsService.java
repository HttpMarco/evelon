package dev.httpmarco.evelon.common.credentials;

import dev.httpmarco.osgon.configuration.ConfigHelper;

public final class CredentialsService {

    private CredentialsConfig credentialsConfig;

    CredentialsService() {
        this.update();
    }

    public void update() {
        this.credentialsConfig = ConfigHelper.getConfigOrCreate("credentials.json", CredentialsConfig.class, new CredentialsConfig());
    }
}
