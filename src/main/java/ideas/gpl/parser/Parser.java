package ideas.gpl.parser;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface Parser<T> {
	List<T> read() throws IOException;

	Map<String, List<T>> groupByName() throws IOException;

	boolean write(Path path, List<String> lines) throws IOException;

}
