import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;

import java.net.URI;



public class ReadHDFS {
    public static void main(String[] args) throws Exception {

        // File to read in HDFS
        String uri = "hdfs://namenode.enisei:8020";
        String inputFile= args[0];

        Configuration conf = new Configuration();
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        // Get the filesystem - HDFS
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        FileStatus[] fileStatus = fs.listStatus(new Path(uri + inputFile));
        //4. Using FileUtil, getting the Paths for all the FileStatus
        Path[] paths = FileUtil.stat2Paths(fileStatus);
        //5. Iterate through the directory and display the files in it
        System.out.println("***** Contents of the Directory *****");
        for(Path path : paths)
        {
            System.out.println(path);
        }
    }
}