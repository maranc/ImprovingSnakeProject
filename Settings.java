import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Settings {

    //current list: Snake Size, Speed
    private ArrayList<Integer> fileValues;
    private Scanner input;

    public Settings(String fileName) {
        try {
            this.fileValues = new ArrayList<>();
            this.input = new Scanner(new File(fileName));
            System.out.println("Read file");
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found");
        }
    }

    private void read() {
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] words = line.split(" ");
            System.out.printf("%s: %s\n", words[0], words[1]);
            int value = Integer.parseInt(words[1]);
            fileValues.add(value);
        }
    }

    public ArrayList<Integer> readFile() {
        this.read();
        input.close();
        return this.fileValues;
    }

    public ArrayList<Integer> getFileValues() {
        return this.fileValues;
    }
}
