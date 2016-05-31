package ideas.gpl;

import ideas.gpl.bean.ServerApplication;
import ideas.gpl.parser.CsvParser;
import ideas.gpl.parser.Parser;
import ideas.gpl.registry.ApplicationRegistry;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) throws IOException {
		
		String fileName = System.getProperty("user.dir") + "/input.txt";
		// App.class.getClassLoader().getResource("input.txt").getPath();
		Parser<ServerApplication> csParser = new CsvParser<ServerApplication>(
				Paths.get(fileName), ServerApplication.class);

		Map<String, List<ServerApplication>> serverApps = csParser
				.groupByName();

		List<String> depricated = serverApps
				.entrySet()
				.stream()
				.filter(p -> ApplicationRegistry.isAnyAppDepricated(p
						.getValue())).map(Map.Entry::getKey)
				.collect(Collectors.toList());
		Path outPath = Paths.get(Paths.get(fileName).getParent().toString()
				+ "/output.txt");
		csParser.write(outPath, depricated);

	}
}
