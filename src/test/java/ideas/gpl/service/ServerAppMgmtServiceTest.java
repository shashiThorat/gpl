package ideas.gpl.service;

import static ideas.gpl.config.Config.INPUT_FILE_PATH;
import static org.junit.Assert.assertEquals;
import ideas.gpl.GplException;
import ideas.gpl.bean.ServerApplication;

import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ServerAppMgmtServiceTest {

	ServerAppMgmtService appMgmt = new ServerAppMgmtService();

	@Test
	public void logOldAppServers() throws GplException {

		appMgmt.logOldAppServers(INPUT_FILE_PATH);

	}

	@Test
	public void getAllApps() throws GplException {

		List<ServerApplication> apps = appMgmt.getAllApps(INPUT_FILE_PATH);
		assertEquals(apps.size(), 6);

	}

	@Test
	public void getOldAppServers() throws GplException {

		assertEquals(appMgmt.getOldAppServers(INPUT_FILE_PATH).size(), 2);

	}

	@Test
	public void getAppGroupByServer() throws GplException {

		assertEquals(appMgmt.getAppGroupByServer(INPUT_FILE_PATH).keySet()
				.size(), 3);

	}

	@Test(expected = GplException.class)
	public void getAppGroupByServerInvalidInput() throws GplException {

		appMgmt.getAppGroupByServer(Paths.get(""));

	}

	@Test(expected = GplException.class)
	public void getAllAppsInvalidInput() throws GplException {

		ServerAppMgmtService appMgmt = new ServerAppMgmtService();

		appMgmt.getAllApps(Paths.get(""));

	}

}
