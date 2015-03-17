package ca.javajesus.saves;

import ca.javajesus.saves.ReadFile;
import ca.javajesus.saves.WriteFile;
import ca.javajesus.game.*;
import java.io.IOException;

public class FileData
{

    public void save() throws IOException{
    {
        String file_name = "res/Saves/JavaTest.txt";
        try{
            ReadFile file = new ReadFile(file_name);
            String[] aryLines = file.OpenFile();
            int i;
            for(i = 0; i < aryLines.length; i++)
            {
                System.out.println(aryLines[i]);
            }
        }
        catch(IOException e){
            System.out.println("LOLNOPE");
        }
        
        WriteFile data = new WriteFile(file_name, true);
        String x = "" + Game.player.getX() + " " + Game.player.getY() + "";
        data.writeToFile(x);
    }
    }
    public String one() throws IOException{
        {
            String x;
            String file_name = "res/Saves/JavaTest.txt";
            try{
                ReadFile file = new ReadFile(file_name);
                String[] aryLines = file.OpenFile();
                x = aryLines[aryLines.length-1];
                return x;
            }
            catch(IOException e){
                return "NOPE";
            }
        }  
    }
    
}