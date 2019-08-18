package classes;

import org.xerial.snappy.Snappy;
import org.xerial.snappy.SnappyInputStream;
import org.xerial.snappy.SnappyOutputStream;

import java.io.*;
import java.util.ConcurrentModificationException;

public class Serializer {

    public static Object deserialize( byte[] bytes ) throws IOException, ClassNotFoundException
    {
        ObjectInputStream in = null;
        ByteArrayInputStream bin = null;
        SnappyInputStream sis = null;

        Object obj;
        try
        {
            bin = new ByteArrayInputStream( bytes );
            sis = new SnappyInputStream(bin);
            in = new ObjectInputStream( sis );
            obj = in.readObject();
        }
        finally
        {
            try {
                if( in != null )
                {
                    in.close();
                }
                if( sis != null )
                {
                    sis.close();
                }
                if( bin != null)
                {
                    bin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    public static byte[] serialize( Object value, int attempt ) throws Exception
    {
        byte[] serializedObj;
        ByteArrayOutputStream bout = null;
        ObjectOutputStream out = null;
        SnappyOutputStream sos = null;


        try
        {
            bout = new ByteArrayOutputStream();
            sos = new SnappyOutputStream(bout);
            out = new ObjectOutputStream( sos );
            out.writeObject( value );
            out.flush();
            serializedObj = bout.toByteArray();
            bout.flush();
            sos.close();
        }
        catch( ConcurrentModificationException e )
        { if( attempt > 3 )
            {
                throw new Exception( "Object being serialized is already occupied by another thread" , e );
            }

            try
            {
                Thread.sleep( 500 );
            }
            catch( InterruptedException e1 )
            {
                e.printStackTrace();
            }

            serializedObj = serialize( value, ++attempt );
        }
        finally
        {
            try {
                if( out != null )
                {
                    out.close();
                }
                if( sos != null )
                {
                    sos.close();
                }
                if( bout != null )
                {
                    bout.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  serializedObj;
    }

    protected static byte[] compressByteArray( byte[] array )
    {
        try
        {
            return Snappy.compress( array );
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }

        return null;
    }

    protected static byte[] decompressByteArray( byte[] array )
    {
        try
        {
            return Snappy.uncompress( array );
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }

        return null;
    }
}
