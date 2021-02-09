package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:config.properties")
public interface ServerConfig extends Config {

	@Key("url.otus")
	String urlOtus();

	@Key("user.email")
	String userEmail();

	@Key("user.password")
	String userPassword();
}
