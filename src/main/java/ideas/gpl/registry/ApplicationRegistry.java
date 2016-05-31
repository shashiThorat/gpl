package ideas.gpl.registry;

import ideas.gpl.bean.ServerApplication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ApplicationRegistry {

	static String VERSION_SEPRATOR = "\\.";

	private static Map<String, Map<String, List<Integer>>> currentAppMap = new HashMap<String, Map<String, List<Integer>>>();
	private static ApplicationRegistry appRegistry = new ApplicationRegistry();

	private ApplicationRegistry() {
	};

	public static ApplicationRegistry getInstance() {
		return appRegistry;
	}

	public Application register(String name, String type, String version) {

		List<Integer> appVersion = getVersion(version);

		Map<String, List<Integer>> appMap = currentAppMap.get(type);
		if (appMap == null) {
			appMap = new HashMap<String, List<Integer>>();
			appMap.put(name, appVersion);
			currentAppMap.put(type, appMap);
		} else {
			List<Integer> latestVersion = appMap.get(name);
			if (latestVersion == null) {
				appMap.put(name, appVersion);
			} else {
				if (compareVersion(latestVersion.iterator(),
						appVersion.iterator()) < 0) {
					appMap.put(name, appVersion);
				}

			}
		}

		return new Application(type, name, appVersion);

	}

	private int compareVersion(Iterator<Integer> latestVersion,
			Iterator<Integer> appVersion) {
		while (latestVersion.hasNext() && appVersion.hasNext()) {
			int diff = latestVersion.next() - appVersion.next();
			if (diff != 0)

				return diff;

		}
		if (latestVersion.hasNext())
			return 1;
		if (latestVersion.hasNext())
			return -1;

		return 0;
	}

	private static List<Integer> getVersion(String version) {
		Scanner scanner = new Scanner(version);
		scanner.useDelimiter(VERSION_SEPRATOR);
		List<Integer> appVersion = new ArrayList<Integer>();
		if (scanner.hasNext())
			appVersion.add(scanner.nextInt());
		if (scanner.hasNext())
			appVersion.add(scanner.nextInt());
		if (scanner.hasNext())
			appVersion.add(scanner.nextInt());
		scanner.close();

		return appVersion;

	}

	public static boolean isAnyAppDepricated(
			Collection<ServerApplication> application) {

		ServerApplication serverApplication = application
				.stream()
				.filter(p -> (!p.getApplication().version.equals(currentAppMap
						.get(p.getApplication().applicationType).get(
								p.getApplication().applicationName))))
				.findFirst().orElse(null);

		return serverApplication == null ? false : true;

	}

	public class Application {

		String applicationType;
		String applicationName;

		List<Integer> version;

		public Application(String applicationType, String applicationName,
				List<Integer> version) {
			super();
			this.applicationType = applicationType;
			this.applicationName = applicationName;
			this.version = version;
		}

		public String getApplicationType() {
			return applicationType;
		}

		public void setApplicationType(String applicationType) {
			this.applicationType = applicationType;
		}

		public String getApplicationName() {
			return applicationName;
		}

		public void setApplicationName(String applicationName) {
			this.applicationName = applicationName;
		}

		public List<Integer> getVersion() {
			return version;
		}

		public void setVersion(List<Integer> version) {
			this.version = version;
		}

		@Override
		public String toString() {
			return "Application [applicationType=" + applicationType
					+ ", applicationName=" + applicationName + ", version="
					+ version + "]";
		}

	}

}
