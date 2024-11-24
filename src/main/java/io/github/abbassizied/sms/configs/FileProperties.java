package io.github.abbassizied.sms.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file.allowed")
public class FileProperties {

    private String[] extensions;
    private String[] mimeTypes;

    public String[] getExtensions() {
        return extensions;
    }

    public void setExtensions(String[] extensions) {
        this.extensions = extensions;
    }

    public String[] getMimeTypes() {
        return mimeTypes;
    }

    public void setMimeTypes(String[] mimeTypes) {
        this.mimeTypes = mimeTypes;
    }
}
