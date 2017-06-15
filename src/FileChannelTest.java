import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {

    public static void main (String[] args) throws Exception {


        RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();// this is where buffer is turned from write mode to read mode

            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }

            //Once you have read all the data, you need to clear the buffer,
            // to make it ready for writing again
            buf.clear();
            // or buffer.compact()
            //The compact() method only clears the data which you have already read
            bytesRead = inChannel.read(buf);
        }
        aFile.close();


    }

}