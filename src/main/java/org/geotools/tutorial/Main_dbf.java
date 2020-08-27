package org.geotools.tutorial;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;

import org.geotools.data.shapefile.dbf.DbaseFileHeader;
import org.geotools.data.shapefile.dbf.DbaseFileReader;
import org.geotools.data.shapefile.files.ShpFiles;
import org.geotools.data.shapefile.shp.ShapefileException;
import org.geotools.data.shapefile.shp.ShapefileReader;
import org.geotools.data.shapefile.shp.ShapefileReader.Record;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;


/**
 * Hello world!
 *
 */
public class Main_dbf
{
    public static void main( String[] args ) throws Exception 
    {
    	DbaseFileReader r = null;
        try {
            ShpFiles shpFile = new ShpFiles
            		("C:\\\\Users\\\\ETRI\\\\Desktop\\\\하수관로(SWL_PIPE_LM)\\\\SWL_PIPE_LM_Down3.dbf");
            r = new DbaseFileReader(shpFile, false, Charset.defaultCharset());
            
            DbaseFileHeader header = r.getHeader();
            
            int i=0;
            int rev=0;
            while (r.hasNext()) {
                Object[] values = r.readEntry();
                double begin = (Double) r.readField(14);
                double end = (Double) r.readField(15);
              
                
                System.out.println("속성 : " + begin+ "  "+end);
                if(begin>end) {rev++;}
                
                System.out.println("---------------");
                i++;
            }
            System.out.println("데이터 갯수 : "+i);
            System.out.println("오류 데이터 갯수 : "+rev);

            r.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  
        
    }
}
