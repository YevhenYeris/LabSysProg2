package yrs.yvhn;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Main {

    public static void main(String[] args) {


        try {
            StringBuilder detailedReport = new StringBuilder();

            DefinedLengthInputGenerator generator = new DefinedLengthInputGenerator("input.txt");
            FiniteStateMachine machine = new FiniteStateMachine("automata.txt");

            boolean success = true;
            String input;
            while ((input = generator.GetNextWord()) != null && success) {
                boolean accepts = machine.DoesAccept(input);

                detailedReport.append(input + " : " + accepts + " : " + machine.GetCurrentState() + "\n");

                if (!accepts) {
                    success = false;
                    System.out.println(input + " : " + false + " : " + machine.GetCurrentState());
                }
                machine.Reload();
            }
            if (success) {
                System.out.println("Автомат сприймає всі можливі слова алфавіту заданої довжини");
            }
            else {
                System.out.println("Автомат сприймає не всі можливі слова алфавіту заданої довжини");
            }

            File resultFile = new File("result.txt");
            resultFile.createNewFile();
            Writer writer = new FileWriter(resultFile);
            writer.write(detailedReport.toString());
            writer.close();

            /*System.out.println("...testing alphabet");
            while ((input = generator.GetNextWord()) != null) {
                boolean accepts = machine.DoesAccept(input);

                detailedReport.append(input + " : " + accepts + "\n");

                machine.Reload();
            }
            writer = new FileWriter(resultFile);
            writer.write(detailedReport.toString());
            writer.close();
            System.out.println("test finished");*/
        }
        catch (IOException ex)
        {
            System.out.println(ex.getStackTrace());
        }
    }
}
