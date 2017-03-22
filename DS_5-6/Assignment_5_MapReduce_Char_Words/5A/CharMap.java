package exp5a;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CharMap extends Mapper<LongWritable, Text, Text, IntWritable>
{
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
	{
		String line = value.toString();
		char[] c_arr = line.toCharArray();
		
		for (char c : c_arr)
		{
			System.out.println(c);
			context.write(new Text(String.valueOf(c)), new IntWritable(1));
		}
		
	}  	
}


