package sample;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.*;

public class SaveSystem {
    /**
     *save the particular type of data to the file you want to name
     * @param path the file name you want to set
     * @param key list contains the name of the data you want to save
     * @param value list contains the content you want to save
     * @throws FileNotFoundException
     */

    public void SaveToFile(String path, List<String> key, List<String> value) throws FileNotFoundException {
        String savepath;
        int length = key.size();
        File savefile = new File(path);
        PrintWriter filenName = new PrintWriter(savefile);
        while (length>=0){
            length--;
            filenName.println(key.get(length-1)+ " : ");
            filenName.println("  " + value.get(length-1));

        }
        filenName.close();
    }

    /**
     * load the content of the chose file
     * @param filename file name you want to load
     * @param key list that would contain the data from file
     * @param value list that would contain the data from file
     * @throws FileNotFoundException
     */
    public void LoadFile(String filename, List<String> key, List<String> value) throws FileNotFoundException {
        File thefile = new File(filename);
        Scanner scan = new Scanner(thefile);
        int length = key.size();
        int i=0;
        while(length>=0){
            key.set(i,scan.nextLine());
            value.set(i,scan.nextLine());
            length--;
            i++;
        }
    }
}

