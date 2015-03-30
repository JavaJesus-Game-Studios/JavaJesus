package ca.javajesus.saves;

import ca.javajesus.saves.ReadFile;
import ca.javajesus.saves.WriteFile;
import ca.javajesus.game.*;
import ca.javajesus.items.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileData
{
    Convert c = new Convert();
    public void save() throws IOException{
    {
        String file_name = "res/Saves/JavaTest.txt";
        try{
            ReadFile file = new ReadFile(file_name);
            String[] aryLines = file.OpenFile();
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
    
    public List<Item> loadGuns()
    {
        String x;
        ArrayList<Item> returnGuns = new ArrayList<Item>();
        try{
            ReadFile file = new ReadFile("res/Saves/JavaGuns.txt");
            String[] aryLines = file.OpenFile();
            for(int i = 0; i < aryLines.length; i++)
            {
                x = aryLines[i];
                Gun g = (Gun)Item.returnItem(x);
                returnGuns.add(g);
            }
            return returnGuns;
        }
        catch(IOException e){
            return null;
        }
    }
    
    public void saveItems() throws IOException{
        {
            String file_name = "res/Saves/JavaItems.txt";
            try{
                ReadFile file = new ReadFile(file_name);
                String[] aryLines = file.OpenFile();
            }
            catch(IOException e){
                System.out.println("LOLNOPE");
            }
            
            WriteFile data = new WriteFile(file_name, true); 
            
            for(Item e: Game.player.inventory.items)
            {
            String x = "" + e;
            data.writeToFile(x);
            }
            
        }
        }
    
    public List<Item> loadItems()
    {
        String x;
        ArrayList<Item> returnGuns = new ArrayList<Item>();
        try{
            ReadFile file = new ReadFile("res/Saves/JavaItems.txt");
            String[] aryLines = file.OpenFile();
            for(int i = 0; i < aryLines.length; i++)
            {
                x = aryLines[i];
                Item g = Item.returnItem(x);
                returnGuns.add(g);
            }
            return returnGuns;
        }
        catch(IOException e){
            return null;
        }
    }
    
    public void saveSwords() throws IOException{
        {
            String file_name = "res/Saves/JavaSwords.txt";
            try{
                ReadFile file = new ReadFile(file_name);
                String[] aryLines = file.OpenFile();
            }
            catch(IOException e){
                System.out.println("LOLNOPE");
            }
            
            WriteFile data = new WriteFile(file_name, true); 
            
            for(Item e: Game.player.inventory.swords)
            {
            String x = "" + e;
            data.writeToFile(x);
            }
            
        }
        }
    
    public List<Item> loadSwords()
    {
        String x;
        ArrayList<Item> returnGuns = new ArrayList<Item>();
        try{
            ReadFile file = new ReadFile("res/Saves/JavaSwords.txt");
            String[] aryLines = file.OpenFile();
            for(int i = 0; i < aryLines.length; i++)
            {
                x = aryLines[i];
                Sword g = (Sword)Item.returnItem(x);
                returnGuns.add(g);
            }
            return returnGuns;
        }
        catch(IOException e){
            return null;
        }
    }
    
    public void saveUsables() throws IOException{
        {
            String file_name = "res/Saves/JavaUsables.txt";
            try{
                ReadFile file = new ReadFile(file_name);
                String[] aryLines = file.OpenFile();
            }
            catch(IOException e){
                System.out.println("LOLNOPE");
            }
            
            WriteFile data = new WriteFile(file_name, true); 
            
            for(Item e: Game.player.inventory.usables)
            {
            String x = "" + e;
            data.writeToFile(x);
            }
            
        }
        }
    
    public List<Item> loadUsables()
    {
        String x;
        ArrayList<Item> returnGuns = new ArrayList<Item>();
        try{
            ReadFile file = new ReadFile("res/Saves/JavaUsables.txt");
            String[] aryLines = file.OpenFile();
            for(int i = 0; i < aryLines.length; i++)
            {
                x = aryLines[i];
                Item g = Item.returnItem(x);
                returnGuns.add(g);
            }
            return returnGuns;
        }
        catch(IOException e){
            return null;
        }
    }
    
    public void saveMisc() throws IOException{
        {
            String file_name = "res/Saves/JavaMisc.txt";
            try{
                ReadFile file = new ReadFile(file_name);
                String[] aryLines = file.OpenFile();
            }
            catch(IOException e){
                System.out.println("LOLNOPE");
            }
            
            WriteFile data = new WriteFile(file_name, true); 
            
            for(Item e: Game.player.inventory.misc)
            {
            String x = "" + e;
            data.writeToFile(x);
            }
            
        }
        }
    
    public List<Item> loadMisc()
    {
        String x;
        ArrayList<Item> returnGuns = new ArrayList<Item>();
        try{
            ReadFile file = new ReadFile("res/Saves/JavaMisc.txt");
            String[] aryLines = file.OpenFile();
            for(int i = 0; i < aryLines.length; i++)
            {
                x = aryLines[i];
                Item g = Item.returnItem(x);
                returnGuns.add(g);
            }
            return returnGuns;
        }
        catch(IOException e){
            return null;
        }
    }
}