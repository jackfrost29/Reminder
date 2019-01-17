package com.example.jack.reminder;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.List;

public class DataHandler {

    static Context context;
    static String countFileName = "countFileName", dataFileName = "dataFileName";
    static List<Item> items;

    static Item getItem(int i){
        return items.get(i);
    }
    static void setItem(Item x, int i){
        items.set(i, x);
    }
    static List<Item> getFullList(){
        return items;
    }
    static void deleteItem(int i){
        items.remove(i);
    }
    static void setNewItem(Item item){
        items.add(item);
    }

    static void load(Context mContext) throws IOException, ClassNotFoundException {
        context = mContext;

        File file = new File(context.getFilesDir(), countFileName);
        FileInputStream fin = new FileInputStream(file);

        int i;
        StringBuilder sb = new StringBuilder();
        while ((i = fin.read()) != -1){
            sb.append((char)i);
        }
        int line = Integer.parseInt(sb.toString());

        file = new File(context.getFilesDir(), dataFileName);
        fin = new FileInputStream(file);
        ObjectInputStream oin = new ObjectInputStream(fin);

        while (line != 0){
            Object o = (Object)oin.readObject();
            Item x;
            if(o instanceof Reminder)
                x = (Reminder)o;
            else if(o instanceof ListItem)
                x = (ListItem)o;
            else
                x = (Note)o;

            // insert it to the arraylist

            items.add(x);
            line--;
        }
    }

    static void save()throws IOException{
        File file = new File(context.getFilesDir(), countFileName);
        FileOutputStream fout = new FileOutputStream(file);

        fout.write(Integer.toString(items.size()).getBytes());
        fout.close();

        file = new File(context.getFilesDir(), dataFileName);
        fout = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fout);

        for(int i=0; i<items.size(); i++){
            oos.writeObject(items.get(i));
        }
        oos.close();
        fout.close();

    }


}
