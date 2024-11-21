package io.github.abbassizied.sms.configs;

import java.nio.file.Paths;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {
	/**
	 * Folder location for storing files
	 */
 
	// public String location = System.getProperty("user.dir")+"/src/main/resources/static/uploads";
	private String location = Paths.get("").toAbsolutePath().toString() + "/src/main/resources/static/uploads";

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}