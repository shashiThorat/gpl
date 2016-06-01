package ideas.gpl.parser;

import ideas.gpl.GplException;
import ideas.gpl.bean.Parsable;
import ideas.gpl.factory.ParsableFactory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

	public List<T> read() throws GplException {
		Stream<String> lines = null;
		try {
			lines = Files.lines(infilepath);

			List<T> application = lines.map(factory.mapToParsable).collect(
					Collectors.toList());

			lines.close();

			return application;
		} catch (IOException | UncheckedIOException e) {
			throw new GplException(e);
		} finally {
			if (lines != null)
				lines.close();
		}

	}

	public Map<String, List<T>> groupByName() throws GplException {

		Stream<String> lines = null;
		try {
			lines = Files.lines(infilepath);

			Map<String, List<T>> serverApps = lines.map(factory.mapToParsable)
					.collect(Collectors.groupingBy(T::getName));

			return serverApps;
		} catch (IOException | UncheckedIOException e) {
			throw new GplException(e);
		} finally {
			if (lines != null)
				lines.close();
		}

	}

	
}
