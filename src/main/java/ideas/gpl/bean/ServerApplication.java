package ideas.gpl.bean;

import ideas.gpl.registry.ApplicationRegistry;

public class ServerApplication implements Parsable {

	private String name;

	ApplicationRegistry.Application application;

	public ServerApplication() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void read(String... attributes) {
		// TODO Auto-generated method stub

		name = attributes[0].trim();
		application = ApplicationRegistry.getInstance().register(
				attributes[1].trim(), attributes[2].trim(),
				attributes[3].trim());

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ApplicationRegistry.Application getApplication() {
		return application;
	}

	public void setApplication(ApplicationRegistry.Application application) {
		this.application = application;
	}

	@Override
	public String toString() {
		return "ServerApplication [name=" + name + ", application="
				+ application + "]";
	}

}
