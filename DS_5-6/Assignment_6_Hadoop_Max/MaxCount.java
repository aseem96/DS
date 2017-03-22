package exp6;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MaxCount extends Configured implements Tool {
	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new MaxCount(), args);

		System.exit(res);
	}

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = getConf();
		@SuppressWarnings("deprecation")
		Job job = new Job(conf, "MaxCount");
		job.setJarByClass(MaxCount.class);
		job.setMapperClass(MaxMap.class);
		job.setNumReduceTasks(0);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		job.setInputFormatClass(org.apache.hadoop.mapreduce.lib.input.TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		Path outputpath = new Path(args[1]);
		outputpath.getFileSystem(conf).delete(outputpath, true);

		job.waitForCompletion(true);

		FileSystem fs = FileSystem.get(conf);
		FileStatus[] status = fs.listStatus(new Path(args[1]));

		// copy hdfs output file to local file
		for (int i = 0; i < status.length; i++) {
			System.out.println(status[i].getPath());
			fs.copyToLocalFile(false, status[i].getPath(), new Path("/home/" + args[1]));
		}

		System.out.println("\nLine\tFirst\tSecond\tMaximum");
		System.out.println("no \tColumn\tColumn\n");

		System.out.println(args[1]);
		// display contents of local file
		BufferedReader br = new BufferedReader(new FileReader("/home/" + args[1]));
		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();

		Scanner s = new Scanner(new File("/home/" + args[1]));
		List<Integer> max_values = new ArrayList<Integer>();

		while (s.hasNext()) {
			s.next();
			s.next();
			s.next();
			max_values.add(Integer.parseInt(s.next()));
		}
		int maximum = 0;

		for (int max : max_values) {
			if (max > maximum) {
				maximum = max;
			}
		}

		System.out.println("\nOverall Maximum: " + maximum + "\n");
		s.close();
		return 0;
	}
}