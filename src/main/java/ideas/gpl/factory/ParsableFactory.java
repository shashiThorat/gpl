package ideas.gpl.factory;

import ideas.gpl.bean.Parsable;

import java.util.function.Function;

public class ParsableFactory<T extends Parsable> {

	Class<T> parsedClass = null;

	public   Function<String, T> mapToParsable = (line) -> {
		T parseObject = null;
		try {
			
			parseObject = parsedClass.newInstance();
			 String attr[]=line.split(",");
			parseObject.read(attr);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return parseObject;
	};
	

	public ParsableFactory(Class<T> parsedClass) {
		this.parsedClass = parsedClass;
	}

	
}
