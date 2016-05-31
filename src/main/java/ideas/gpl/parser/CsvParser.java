package ideas.gpl.parser;

import ideas.gpl.bean.Parsable;
import ideas.gpl.factory.ParsableFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvParser<T extends Parsable> implements Parser<T> {

	ParsableFactory<T> factory;
	Path infilepath;

	public CsvParser(Path path, Class<T> parsedClass) {
		factory = new ParsableFactory<T>(parsedClass);
		infilepath = path;
	}

	public List<T> read() throws IOException {

		Stream<String> lines = Files.lines(infilepath);

		List<T> application = lines.map(factory.mapToParsable).collect(
				Collectors.toList());

		lines.close();

		return application;

	}

	public Map<String, List<T>> groupByName() throws IOException {

		Stream<String> lines = Files.lines(infilepath);

		Map<String, List<T>> serverApps = lines.map(factory.mapToParsable)
				.collect(Collectors.groupingBy(T::getName));

		lines.close();

		return serverApps;

	}

	public boolean write(Path path, List<String> lines) throws IOException {

		Files.write(path, lines, StandardCharsets.UTF_8,
				StandardOpenOption.CREATE);

		return true;

	}
}
