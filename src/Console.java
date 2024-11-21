import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

public class Console {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        String readFile, writeFile;
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-h")) {
                System.out.println("flags:\n -r : read file\n -w : write file\n -GUI : GUI");
                break;
            }
            if (args[i].startsWith("-GUI")) Graphics.main(args);
            if (args[i].startsWith("-r")) {
                if (i + 1 < args.length + 1) {
                    readFile = args[i + 1];
                    if (args[i + 2].startsWith("-w")) {
                        if (i + 3 < args.length + 1) {
                            writeFile = args[i + 3];
                            try {
                                List<Integer> list = ArrayConverter.convertToList(FileIO.readStringFromFile(readFile).split(" "));
                                Logic.process(list);
                                FileIO.writeStringToFile(writeFile, ArrayConverter.listToString(list));
                            } catch (Exception e) {
                                System.out.println("Error");
                                System.exit(0);
                            }
                        }
                    }
                }
            }
        }
    }
}
