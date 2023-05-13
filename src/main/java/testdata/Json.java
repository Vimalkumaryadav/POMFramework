package testdata;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static constants.Constants.*;


public class Json {

	private JSONObject jsonObject;
	
	// Read File
	public void load(final String fileName) throws IOException, ParseException{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader( ROOT_DIRECTORY + "" +fileName));
		this.jsonObject=(JSONObject) obj;
	}
	
	//Read as key/value pair
	public Object getValue(final String key) { return this.jsonObject.get(key);}
	
	//Read as array
	public JSONArray getArray(final String key) { return (JSONArray) this.jsonObject.get(key);}
	
	//Read as DataProvider
	
	public @NotNull Iterator<Object[]> getMap(final String key){
		@SuppressWarnings("unchecked")
		ArrayList<Map<?,?>> arrList = (ArrayList<Map<?,?>>) this.jsonObject.get(key);
		Collection<Object[]> dp = new ArrayList<>();
		for(Map<?,?> map : arrList) {
			dp.add(new Object[] {map});
			}
		return dp.iterator();
	}
}
