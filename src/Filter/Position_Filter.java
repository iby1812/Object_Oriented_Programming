package Filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Data_sets.ID;
import Data_sets.Position;
import Data_sets.Time;
import Main_App.Main;

public class Position_Filter {

	public static void Positionfilter(double lat, double lon,double radius) throws IOException, ParseException
	{
		double latcsv,loncsv,altcsv;

		ArrayList<Record_Filter> data_list = new ArrayList<Record_Filter>();
		FileReader fr = new FileReader(Main.file_Out);
		BufferedReader br = new BufferedReader(fr);
		String Line = br.readLine();
		Line = br.readLine();
		
		while(Line != null) 
		{
			String[] arr = (Line.split(","));
			double templat=Double.parseDouble(arr[2]);
			double templon=	Double.parseDouble(arr[3]);	
			if(distance(lat,lon,templat,templon)<=radius)
			{
				
				for (int i = 6; i < arr.length; i=i+4) 
				{
				Date date  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(arr[0]);
				latcsv = Double.parseDouble(arr[2]);
				loncsv = Double.parseDouble(arr[3]);
				altcsv = Double.parseDouble(arr[4]);
				Position pos = new Position(altcsv, loncsv, latcsv);
				Time time = new Time(date);
				ID id=new ID(arr[i]);
				Record_Filter record = new Record_Filter(time, pos,id);
				data_list.add(record);
				}
			}
			
			Line = br.readLine();
		}
		
		br.close();
		
			Filter_2_KML.KML(data_list,"Position");	
		
	}
		
		
	private static double distance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1))*Math.sin(deg2rad(lat2))+Math.cos(deg2rad(lat1))*Math.cos(deg2rad(lat2))*Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;
		
		return (dist)/100;
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts decimal degrees to radians						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
	
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts radians to decimal degrees						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
}
