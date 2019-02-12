package com.githubexample.Printer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.githubexample.IPrinter;
import com.githubexample.entities.Data;

public class TextPrinter implements IPrinter<Data>{//data yani sonuc class i icin caliscak

	private List<Data> result = null;
	
	@Override
	public void Print() {
	 String datetime = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss").format(new Date());
	 File file = new File("output-"+datetime+".txt");
	        
        try {

        	BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
        	 
            if (!file.exists()) {
                    file.createNewFile();
            }
            
            for (Data data : result) {
            	bw.write(data.toString());
            	bw.newLine();
            }
            
            bw.flush();
            bw.close();

            System.out.println("Text dosyasi yazdirildi. Path: " + file.getAbsolutePath());
        } catch (IOException e) {
                e.printStackTrace();
        }
	}

	@Override
	public void SetData(List<Data> data) {
		this.result = data;
	}
	
}
