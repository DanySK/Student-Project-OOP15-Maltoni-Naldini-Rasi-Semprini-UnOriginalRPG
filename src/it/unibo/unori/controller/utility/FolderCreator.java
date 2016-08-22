package it.unibo.unori.controller.utility;
import java.io.File;



public class FolderCreator {
    

        public static void main(String[] args) {
            String Dir = System.getProperty("user.home")+ System.getProperty("file.separator") + "Prova" + System.getProperty("file.separator")+ "Paperino";
            boolean success = (new File(Dir)).mkdirs();

            if (success)
            {
              System.out.println("Ho creato: " + Dir);
            }else{
              System.out.println("Impossibile creare: " + Dir);
            }

        }
    }

