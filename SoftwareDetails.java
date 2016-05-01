import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by himu on 5/1/2016.
 */
public class SoftwareDetails {


    private static final String inputFileName = "input.txt";
    private static final String outputFileName = "output.txt";
    private static String line = null;
    private static String [] tokens;
    private static final Set<String> softwareSet = new HashSet();
//    private static final Map<String, String> softwareVersionMap = new HashMap();
    private static final List<String> softwareNames = new ArrayList();
    private static final List<String> softwareVersions = new ArrayList();
    public static void main(String[] args) {

        Scanner scanner = new Scanner(SoftwareDetails.class.getResourceAsStream(inputFileName));

        while (scanner.hasNextLine()) {

            line = scanner.nextLine();

            tokens = line.split(", ");

            softwareNames.add(tokens[2]);
            softwareVersions.add(tokens[3]);
            softwareSet.add(tokens[2]);
        }
        List<String> finalSoftwareList = new ArrayList();
        List<String> sortedVersions = new ArrayList();
        for(String software : softwareSet){

            if((Collections.frequency((softwareNames), software)>=3)){
//                System.out.println(software);

                for(int i=0; i<softwareNames.size(); i++)
                {
                if(softwareNames.get(i).equalsIgnoreCase(software)){
                    sortedVersions.add(softwareVersions.get(i));

                }
                }

                String maxValue = Collections.max(sortedVersions);
                int count = 0;
                for(int i = 0; i < sortedVersions.size(); i++){
                   if(sortedVersions.get(i).compareTo(maxValue)<0){
                       count++;
                   }
               }
                if(count>=2){
                    finalSoftwareList.add(software);
                }
            }
        }

        File file = new File(outputFileName);

        if(!file.exists()){
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                for(String str: finalSoftwareList){
                    fw.write(str);
                }

                fw.close();
            } catch (IOException e) {
                System.out.println("IO Exception occurred");
            }
        }


    }


    public int compare(String str1, String str2) {
        if(str1 == null || str2 == null)
        return 0;

        String[] thisParts = str1.split("\\.");
        String[] thatParts = str2.split("\\.");
        int length = Math.max(thisParts.length, thatParts.length);
        for(int i = 0; i < length; i++) {
            int thisPart = i < thisParts.length ?
                    Integer.parseInt(thisParts[i]) : 0;
            int thatPart = i < thatParts.length ?
                    Integer.parseInt(thatParts[i]) : 0;
            if(thisPart < thatPart)
                return -1;
            if(thisPart > thatPart)
                return 1;
        }
        return 0;
    }
}
