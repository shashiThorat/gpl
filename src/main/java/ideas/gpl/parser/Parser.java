package ideas.gpl.parser;

import ideas.gpl.GplException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

public interface Parser<T> {
	List<T> read() throws GplException;

	Map<String, List<T>> groupByName() throws GplException;

	//boolean write(Path path, List<String> lines) throws GplException;
	
	public static boolean write(Path path, List<String> lines) throws GplException {
		try {
			Files.write(path, lines, StandardCharsets.UTF_8,
					StandardOpenOption.CREATE);

			return true;
		} catch (IOException e) {
			throw new GplException(e);
		}

	}

}
