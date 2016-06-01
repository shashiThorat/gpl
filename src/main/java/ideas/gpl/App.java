package ideas.gpl;

import static ideas.gpl.config.Config.INPUT_FILE_PATH;
import ideas.gpl.service.ServerAppMgmtService;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) throws GplException {
		
		new ServerAppMgmtService().logOldAppServers(INPUT_FILE_PATH);
		
	}
}
