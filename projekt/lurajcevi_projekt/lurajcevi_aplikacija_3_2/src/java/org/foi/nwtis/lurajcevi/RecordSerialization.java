
package org.foi.nwtis.lurajcevi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.lurajcevi.modeli.JMSPorukaMail;
import org.foi.nwtis.lurajcevi.modeli.JMSPorukaZip;

/**
 * @document RecordSerialization
 * @author Luka Rajcevic
 */
public class RecordSerialization {
    
     /**
     * stores list of Record type object for serialization
     */
    public static List<JMSPorukaMail> recordMail = new ArrayList<JMSPorukaMail>();
    public static List<JMSPorukaZip> recordZip = new ArrayList<JMSPorukaZip>();

    /**
     * Serializes List<Record> to a file
     * @param filename  - writes serialized data to this file
     */
    public static void serializeToFileMail(String filename, List<JMSPorukaMail> list){
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        
        try {
            fileOutputStream = new FileOutputStream(filename);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
        } catch (IOException ex) {
            System.out.println("Failed to serialize.");
            return;
        }
        finally{
            try {
                if (objectOutputStream != null)
                    objectOutputStream.close();
                if (fileOutputStream != null)
                    fileOutputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(RecordSerialization.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Serialized.");
    }
    
        /**
     * Serializes List<Record> to a file
     * @param filename  - writes serialized data to this file
     */
    public static void serializeToFileZip(String filename, List<JMSPorukaZip> list){
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        
        try {
            fileOutputStream = new FileOutputStream(filename);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
        } catch (IOException ex) {
            System.out.println("Failed to serialize.");
            return;
        }
        finally{
            try {
                if (objectOutputStream != null)
                    objectOutputStream.close();
                if (fileOutputStream != null)
                    fileOutputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(RecordSerialization.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    /**
     * Reads from serialized file into the List<Record> type variable
     * @param filename - reads from this file
     */
    public static void deserializeJMSMail(String filename){
        List<JMSPorukaMail> records = new ArrayList<JMSPorukaMail>();
        FileInputStream fileInputStream = null;
        ObjectInputStream in = null;
        try {
            fileInputStream = new FileInputStream(filename);
            in = new ObjectInputStream(fileInputStream);
            records =  (List<JMSPorukaMail>) in.readObject();
        } catch (IOException ex){
            
        } catch(ClassNotFoundException ex2){
            
        } catch (ClassCastException ex3){
            
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
                if (in != null)
                    in.close();
            } catch (IOException ex) {
                Logger.getLogger(RecordSerialization.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (records != null)
            RecordSerialization.recordMail = records;
        else{
            System.out.println("File is empty.");
        }
    }
    
     /**
     * Reads from serialized file into the List<Record> type variable
     * @param filename - reads from this file
     */
    public static void deserializeJMSZip(String filename){
        List<JMSPorukaZip> records = new ArrayList<JMSPorukaZip>();
        FileInputStream fileInputStream = null;
        ObjectInputStream in = null;
        try {
            fileInputStream = new FileInputStream(filename);
            in = new ObjectInputStream(fileInputStream);
            records =  (List<JMSPorukaZip>) in.readObject();
        } catch (IOException ex){
            
        } catch(ClassNotFoundException ex2){
            
        } catch (ClassCastException ex3){
            
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
                if (in != null)
                    in.close();
            } catch (IOException ex) {
                Logger.getLogger(RecordSerialization.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (records != null)
            RecordSerialization.recordZip = records;
        else{
            System.out.println("File is empty.");
        }
    }


}
