package exp6;

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
		String[] words = value.toString().split(" ");
		
		for (String w : words)
		{
			//System.out.println(w);
		    context.write(new Text(w), new IntWritable(1));
		}
		
	}  	
}


