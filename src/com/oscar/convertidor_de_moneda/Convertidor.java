package com.oscar.convertidor_de_moneda;
import org.json.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Convertidor {
		public static void main(String[] args) {
			
			 int numero = 0;
			 String de="";
			 String a="";
			 
			 String opcion = (JOptionPane.showInputDialog(null,"Selecciona el tipo de conversion: ","Conversor",JOptionPane.PLAIN_MESSAGE,null,new Object[] {"Selecciona","Conversion de moneda","Conversion de temperatura"},"Selecciona")).toString();
		        switch (opcion){
		            case "Conversion de moneda":{
		            	try {
		        			String cantidad = JOptionPane.showInputDialog(null,"Cuál es la cantidad a convertir? ");
		        			numero = Integer.parseInt(cantidad);
		        			de = (JOptionPane.showInputDialog(null,"Selecciona la moneda actual: ","Conversor",JOptionPane.PLAIN_MESSAGE,null,new Object[] {"Selecciona","MXN","USD","JPY", "EUR", "GBP"},"Selecciona")).toString();
		        			a = (JOptionPane.showInputDialog(null,"Selecciona la moneda a la que se requiere convertir: ","Conversor",JOptionPane.PLAIN_MESSAGE,null,new Object[] {"Selecciona","MXN","USD","JPY", "EUR", "GBP"},"Selecciona")).toString();
		        		} catch (NumberFormatException e) {
		        			JOptionPane.showMessageDialog(null,"Cantidad incorrecta, este campo solo acepta números enteros!!!!!!.");
		        		}

		        			try {
		        				URL url = new URL("https://v6.exchangerate-api.com/v6/30f1460ee72a155dd7180682/pair/"+de+"/"+a+"/"+numero);
		        				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        				conn.setRequestMethod("GET");
		        				conn.connect();
		        				int responseCode = conn.getResponseCode();
		        				if (responseCode != 200) {
		        					throw new RuntimeException("Ocurrio un error: " + responseCode);
		        				} else {
		        					StringBuilder informationString = new StringBuilder();
		        					Scanner scanner = new Scanner(url.openStream());
		        					while (scanner.hasNext()) {
		        						informationString.append(scanner.nextLine());
		        					}
		        					scanner.close();
		        					JSONObject jsonObject = new JSONObject(informationString.toString());
		        					JSONObject.getNames(jsonObject);
		        					JOptionPane.showMessageDialog(null, ("La conversion da como resultado: " +jsonObject.getInt("conversion_result")+" "+a));
		        				}
		        			} catch (Exception e) {
		        				e.printStackTrace();
		        			}
		                break;
		            }
		            case "Conversion de temperatura":{
		                System.out.println("Usted eligió la opcion 2.");
		                break;
		            }
		        }
			 
			 
		
		}
}