package properties;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import static constants.Constants.PROPERTIES_FILES_PATH;

public class PropertyReader {

	private static PropertyReader instance =null;
	private final Properties lproperties;
	
	private PropertyReader(String fileName) {
		try {
			FileInputStream fis = new FileInputStream(PROPERTIES_FILES_PATH + fileName);
			this.lproperties = new Properties();
			this.lproperties.load(fis);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Unable to read properties file: " + fileName);
		} catch (IOException e) {
			throw new RuntimeException("Unable to load properties file: " + fileName);
		}
	}
	
	public static synchronized PropertyReader getinstance(String fileName) {
		if(instance ==null) {
			instance =  new PropertyReader(fileName);
		}
		return instance;
	}
	
	/*--Get Specific Value For Given Key--*/
	
	public String get(@NotNull String key) {
		String value;
		if(!key.isEmpty()) {
			value = this.lproperties.getProperty(key);
			if(value==null) {
				throw new RuntimeException("Propety value is null, please check the key");
			}
		}else {
			throw new RuntimeException("Property key is empty");
		}
		return value;
	}
	
	/*--Get All Value From Property File--*/
	public Map<String, String> getAll(){
		HashMap<String, String> values = new HashMap<>();
		for(Object key: getAllKeys()) {
			/*--Change Object type to String type--*/
			String k = (String) key;
			values.put(k, get(k));
		}
		return values;
	}
	
	/*--Get All Keys From Property File--*/
	@Contract(pure = true)
	private @NotNull Set<Object> getAllKeys(){
		return this.lproperties.keySet();
	}
}
