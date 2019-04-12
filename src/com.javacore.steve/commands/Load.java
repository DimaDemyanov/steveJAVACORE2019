package commands;

import commandsUtils.Command;
import main.Steve;
import managers.ProfileManager;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Load extends Command {
    public Load() {
        options.addRequiredOption("t", "type", true, "File type");
        options.addRequiredOption("f", "file", true, "File path");
    }

    @Override
    public void perform(Steve steve, String[] options) throws ParseException {
        CommandLine commandLine = parser.parse(this.options, options);
        String fileType = commandLine.getOptionValue('t');
        String name = null, dna = null, fingerPrint = null;
        try (Scanner sc = new Scanner(new File(commandLine.getOptionValue("f")))){
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String [] args = line.split(";");
                if(fileType.equals("profile")){
                    StringBuilder nameSb = new StringBuilder();
                    args[0] = "";
                    Arrays.stream(args).forEach((o) -> nameSb.append(o).append(" "));
                    name = nameSb.toString();
                }
                if(fileType.equals("dna")){
                    dna = args[1];
                }
                if(fileType.equals("fingerprints")){
                    StringBuilder fingerPrintSb = new StringBuilder();
                    for (int i = 0; i < 5; i++) {
                        fingerPrintSb.append(sc.nextLine());
                        fingerPrintSb.append('\n');
                    }
                    fingerPrint = fingerPrintSb.toString();
                }
                int id = Integer.valueOf(args[0]);
                if(!ProfileManager.PROFILE_MANAGER.isExist(id))
                    ProfileManager.PROFILE_MANAGER.addProfile(id, name, dna, fingerPrint);
                else
                    ProfileManager.PROFILE_MANAGER.updateProfile(id, name, dna, fingerPrint);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public String getName() {
        return "load";
    }
}
