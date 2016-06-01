package ideas.gpl.parser;

import static org.junit.Assert.assertEquals;
import ideas.gpl.GplException;
import ideas.gpl.bean.Parsable;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
@RunWith(JUnit4.class)
public class CsvParserTest {
	public static Path INPUT_FILE_PATH = Paths.get(System
			.getProperty("user.dir") + "/student.txt");
	CsvParser<StudentSubjects> csvParser = new CsvParser<CsvParserTest.StudentSubjects>(
			INPUT_FILE_PATH, StudentSubjects.class);

	public static class StudentSubjects implements Parsable {

		Integer marks;
		String name;
		String subject;

		@Override
		public void read(String... attributes) {
			name = attributes[0].trim();
			marks = new Integer(attributes[2].trim());
			subject = attributes[1].trim();

		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return name;
		}

	}
    @Test
	public void read() throws GplException
	{
		assertEquals(csvParser.read().size(),10);
		
		 
	}
    @Test
   	public void groupByName() throws GplException
   	{
   		assertEquals(csvParser.groupByName().keySet().size(),2);
   		
   		 
   	}
}
