package ca.javajesus.saves;

import ca.javajesus.saves.ReadFile;
import ca.javajesus.saves.WriteFile;
import ca.javajesus.game.*;
import ca.javajesus.items.*;

import java.io.IOException;

public class FileData
{
    Convert c = new Convert();
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
        Gun g = (Gun)Game.player.inventory.guns.get(0);
        String gunData = g.toString();
        double ammoData = g.ammo;
        
        WriteFile data = new WriteFile(file_name, true);         
        String x = "" + Game.player.getX() + "a" + Game.player.getY() + "b" + 
                Game.player.getHealth() + "c" + Game.player.stamina + "d" + 
                Game.player.score + "e" + c.strToBinary(Game.player.getName()) 
                + "f" + c.strToBinary(gunData) + "g"/*Game.player.inventory.guns.get(0).id + "g"*/ + ammoData + 
                "h";
        
        data.writeToFile(x);
    }
    }
    public String data(String File) throws IOException{
        {
            String x;
            String file_name = File;
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
    
    public void saveGuns() throws IOException{
        {
            String file_name = "res/Saves/JavaGuns.txt";
            try{
                ReadFile file = new ReadFile(file_name);
                String[] aryLines = file.OpenFile();
                System.out.println(aryLines[aryLines.length-1]);
            }
            catch(IOException e){
                System.out.println("LOLNOPE");
            }
            
            WriteFile data = new WriteFile(file_name, true); 
            
            for(Item e: Game.player.inventory.guns)
            {
            String x = "" + e;
            data.writeToFile(x);
            }
            
        }
        }
    
}