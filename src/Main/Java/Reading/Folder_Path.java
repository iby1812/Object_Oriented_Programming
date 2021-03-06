package Reading;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

/**
 * Analyze Folder to get only WigleWifi files.
 *
 */
public class Folder_Path {

	/**
	 * getFiles by name "WigleWifi" and endWith format ".csv".
	 * @param folder This path to read from the user.
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void getFiles(File folder) throws IOException, ParseException {

		String[] files_names = folder.list();

		for(int i = 0; i < files_names.length; i++) {
			if(files_names[i].startsWith("WigleWifi") && files_names[i].endsWith(".csv")) {
				File file = new File(folder+"/"+files_names[i]);
				Reading.Wigle_File.read(file);

			}
		}	
	}
}
