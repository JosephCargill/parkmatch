package pm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void run(String... strings) throws Exception {
		
		log.info("Creating tables");
		
		jdbcTemplate.execute("DROP TABLE parkingspaces IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE parkingspaces(" + "id SERIAL, location VARCHAR(255), price VARCHAR(255))");
		
		List<Object[]> splitUpEntries = Arrays.asList("Forest 150", "House 135", "Sea 998", "Quarry 3").stream()
			.map(location -> location.split(" "))
			.collect(Collectors.toList());
			
			splitUpEntries.forEach(location -> log.info(String.format("Inserting space record for %s %s", location[0], location[1])));
			
			jdbcTemplate.batchUpdate("INSERT INTO parkingspaces(location, price) VALUES (?, ?)", splitUpEntries);
			
			log.info("Querying for parking space records where location = 'House':");
			jdbcTemplate.query("SELECT id, location, price FROM parkingspaces WHERE location = ?", new Object[] { "House" },
			(rs, rowNum) -> new ParkingSpace(rs.getLong("id"), rs.getString("location"), rs.getString("price"))
			).forEach(parkingspace -> log.info(parkingspace.toString()));
	}
}