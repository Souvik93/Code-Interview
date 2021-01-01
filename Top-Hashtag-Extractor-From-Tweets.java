      
      
     
     /******************************************************************************

 Question
 
Find the top 10 trending hashtags in twitter. You can write a standalone java class(es) / interfaces as deemed
to be fit. 
Assumptions &amp; notes : 
1) A tweet is a text being input by tweeters. 
2) A main method in a java class to be implemented which takes the tweet as an input. 
3) You need to extract hashtag from a tweet text (Ex: sachin is hashtag in the tweet -&gt; &quot; Worlds best cricketer
is #sachin&quot;) 
4) Maintain a data structure that keeps tracking of the count of each hashtag that is coming to your main
method 
5) print the list of top 10 hashtags at the end of main method execution.

*******************************************************************************/
import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.Scanner;
import java.util.regex.*;



public class Main
{
    private static Map<String,Integer> hashtagMap = new TreeMap<>();
    
	public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
 
        //Get input tweets
        System.out.println("Enter Total Number Of Tweets: ");
        String totalTweets = in.nextLine();
        
        int total = Integer.parseInt(totalTweets);
        
        if(total<=0) {
            return;
        }
        
        String tweets[] = new String[total];
        
        int tweetCount = 0;
        
        while(tweetCount<total) {
            System.out.println("Enter Tweet: "+(tweetCount+1)+" Of "+total);
            tweets[tweetCount++] = in.nextLine();
            
        }
        
        Pattern MY_PATTERN = Pattern.compile("#(\\w+)");
        
        for(String tweet: tweets) {
            
            Matcher mat = MY_PATTERN.matcher(tweet);
            while (mat.find()) {
    
                String hashtag = mat.group(1).toLowerCase();     
                if(hashtagMap.containsKey(hashtag)) {
                    hashtagMap.put(hashtag,hashtagMap.get(hashtag)+1);
                } else {
                    hashtagMap.put(hashtag,1);
                }
            }
                
        }
        
        hashtagMap = valueSort(hashtagMap);
        
        // Passed 10 as we need to display top 10 tweets 
        printTweets(10);
        
	}
        
        private static void printTweets(int top) {
            
            int size = hashtagMap.size();
            
            if(size==0) {
                System.out.println("Unable to find any hashtag");
                return;
            }
            
            if(size<=top) {
                System.out.println("Less than "+top+" hashtag found");
                
                System.out.println(hashtagMap);
                return;
            }
            
            Set set = hashtagMap.entrySet();
            
            Iterator i = set.iterator();
            
            while (i.hasNext() && size>top) 
            { 
            	Map.Entry map = (Map.Entry)i.next(); 
            
            	System.out.print(map.getKey() + ": "); 
            
            	System.out.println(map.getValue());
            	size--;
            }
            
            
        }
        
    public static <K, V extends Comparable<V> > Map<K, V> 
	valueSort(final Map<K, V> map) 
	{ 
		Comparator<K> valueComparator = new Comparator<K>() 
		{ 
			
			public int compare(K k1, K k2) 
			{ 

				int comp = map.get(k2).compareTo(map.get(k1)); 

				if (comp == 0) 
					return 1; 

				else
					return comp; 
			} 
		}; 

		// SortedMap created using the comparator 
		Map<K, V> sorted = new TreeMap<K, V>(valueComparator); 

		sorted.putAll(map); 

		return sorted; 
	}
    
    
}
