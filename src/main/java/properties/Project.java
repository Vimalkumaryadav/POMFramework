package properties;

import org.aeonbits.owner.Config;

import com.BestClass.office.enums.Browsers;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
	"system:properties",
	"system:env",
	"file:$(user.dir)/resources/properties/project.properties"
})
public interface Project extends Config{
	
	Browsers browser();
	
	Browsers chromeIncog();
	
	int timeOut();
	
	String OrangeHRM();
	
	String OrangeHRMUserName();
	
	String OrangeHRMPwd();
	
	String testData();
	
	String reportConfig();
	
	String reports();
	
	String reportName();
	
	String snaps();
	
	String appUrl();
	
	String pageTitile();
	
	String pageHeader();
	
	String pageSubHeader();
	
	String appPath();
}
