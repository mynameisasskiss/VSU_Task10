public class CmdArgs {

    String[] args;
    String readFile;
    String writeFile;

    public CmdArgs(String[] args){
        this.args = args;
    }

    public boolean ifConsole(){
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-h")) {
                needHelp();
            }
            if (args[i].startsWith("-r")) {
                if (i + 1 < args.length + 1) {
                    if (args[i + 2].startsWith("-w")) {
                        if (i + 3 < args.length + 1) {
                            this.readFile = args[i + 1];
                            this.writeFile = args[i + 3];
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean ifGUI(){
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-GUI")) return true;
        }
        return false;
    }

    private static void needHelp(){
        System.out.println("flags:\n -r : read file\n -w : write file\n -GUI : GUI");
    }

}
