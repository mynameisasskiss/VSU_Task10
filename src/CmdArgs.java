import java.util.Arrays;

public class CmdArgs {

    public static boolean ifConsole(String[] args){
        String readFile, writeFile;
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-h")) {
                needHelp();
            }
            if (args[i].startsWith("-r")) {
                if (i + 1 < args.length + 1) {
                    if (args[i + 2].startsWith("-w")) {
                        if (i + 3 < args.length + 1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean ifGUI(String[] args){
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-GUI")) return true;
        }
        return false;
    }

    private static void needHelp(){
        System.out.println("flags:\n -r : read file\n -w : write file\n -GUI : GUI");
    }

}
