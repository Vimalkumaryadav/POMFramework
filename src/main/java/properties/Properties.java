package properties;

import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;

public final class Properties {

	@Getter
	static Project project;
	
	static {
		project = ConfigFactory.create(Project.class);
	}
	
	public static Project getProject() {
	       return project;
	   }

}
