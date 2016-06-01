package ideas.gpl.service;

import ideas.gpl.GplException;
import ideas.gpl.bean.ServerApplication;
import ideas.gpl.parser.CsvParser;
import ideas.gpl.parser.Parser;
import ideas.gpl.registry.ApplicationRegistry;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServerAppMgmtService {

	public List<String> getOldAppServers(Path inputResourcepath)
			throws GplException {

		Map<String, List<ServerApplication>> serverApps = getAppGroupByServer(inputResourcepath);
		List<String> depricated = serverApps
				.entrySet()
				.stream()
				.filter(p -> ApplicationRegistry.isAnyAppDepricated(p
						.getValue())).map(Map.Entry::getKey)
				.collect(Collectors.toList());
		;
		return depricated;

	}

	public Map<String, List<ServerApplication>> getAppGroupByServer(
			Path resourcepath) throws GplException {
		Parser<ServerApplication> csParser = new CsvParser<ServerApplication>(
				resourcepath, ServerApplication.class);

		Map<String, List<ServerApplication>> serverApps = csParser
				.groupByName();

		return serverApps;
	}

	public List<ServerApplication> getAllApps(Path resourcepath)
			throws GplException {
		Parser<ServerApplication> csParser = new CsvParser<ServerApplication>(
				resourcepath, ServerApplication.class);

		List<ServerApplication> serverApps = csParser.read();

		return serverApps;
	}

	public void logOldAppServers(Path inputResourcepath) throws GplException {
		List<String> depricated = getOldAppServers(inputResourcepath);
		Path outPath = Paths.get(inputResourcepath.getParent().toString()
				+ "/output.txt");
		Parser.write(outPath, depricated);

	}
}
