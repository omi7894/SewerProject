package org.geotools.tutorial;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;

import org.geotools.data.shapefile.dbf.DbaseFileHeader;
import org.geotools.data.shapefile.dbf.DbaseFileReader;
import org.geotools.data.shapefile.files.ShpFiles;


/*
 shapefile의 속성데이터중 Begin Depth 와 End Depth를 이용하여
 역방향으로 되어있는 하수도 갯수를 찾는다. 
 */

public class Main_dbf
{
    public static void main( String[] args ) throws Exception 
    {
    	DbaseFileReader r = null;
        try {
            ShpFiles shpFile = new ShpFiles
            		("C:\\Users\\ETRI\\Desktop\\하수관로(SWL_PIPE_LM)\\SWL_PIPE_LM_Down3.dbf");
            r = new DbaseFileReader(shpFile, false, Charset.defaultCharset());
            
            DbaseFileHeader header = r.getHeader();
            
            int numOfData=0; //전체 데이터 갯수
            int rev=0; //역방향 데이터 갯수
            while (r.hasNext()) {
                Object[] values = r.readEntry();
                double begin = (Double) values[14];
                double end = (Double) values[15];
                
                if(begin>end) {rev++;}
                
                numOfData++;
            }
            System.out.println("데이터 갯수 : "+numOfData);
            System.out.println("오류 데이터 갯수 : "+rev);

            r.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  
        
    }
}
