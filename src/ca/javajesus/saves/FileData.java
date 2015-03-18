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
            System.out.println(aryLines[aryLines.length-1]);
        }
        catch(IOException e){
            System.out.println("LOLNOPE");
        }
        
        WriteFile data = new WriteFile(file_name, true);
        String x = "" + Game.player.getX() + "a" + Game.player.getY() + "b" + 
                Game.player.getHealth() + "c" + Game.player.stamina + "d" + 
                Game.player.score + "e" + Game.player.getName();
        data.writeToFile(x);
    }
    }
    public String data() throws IOException{
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