package exp6;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxMap extends Mapper<LongWritable, Text, Text, IntWritable> {
	int values[] = new int[10000];
	int values1[] = new int[10000];
	String word[];
	int maxValue = 0, linenum = 0;

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String words = value.toString();
		System.out.println(words);
		word = words.split(",");
		for (int i = 0; i < 2; i++) {
			System.out.println(word[i]);
			values[i] = Integer.parseInt(word[i]);
			values1[i] = Integer.parseInt(word[i]);
		}
		if (values1[0] < values1[1]) {
			int temp = values1[0];
			values1[0] = values1[1];
			values1[1] = temp;
		}
		maxValue = values1[0];
		String text = "" + (linenum + 1) + "\t" + values[0] + "\t" + values[1] + "";
		if (linenum >= 0) {
			context.write(new Text(text), new IntWritable(maxValue));
		}
		linenum++;
	}
}