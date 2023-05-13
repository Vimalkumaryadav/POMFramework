package properties;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.jetbrains.annotations.NotNull;

public class Utilities {
	
	public String encode(@NotNull String string) {
	    return Base64.getEncoder().encodeToString(string.getBytes());
	}

	
	public String decode(@NotNull String encodedValue) {
		return new String(Base64.getDecoder().decode(encodedValue.getBytes()));
	}
	
	public String currentDateTime() {
	    LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
	    return now.format(formatter);
	}
	
	public String localTime() {
		return LocalTime.now().toString();
	}
	
	public String localTime(String zoneID) {
		return LocalTime.now(ZoneId.of(zoneID)).toString();
	}
	
	public long epochMilliSeconds() {
		Instant instant = Instant.now();
		return instant.toEpochMilli();
	}
	
	public long epochSeconds() {
		Instant instant = Instant.now();
		return instant.getEpochSecond();
	}
}
