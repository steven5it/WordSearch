/*
  File: WordSearch.java

  Description: given a text file with a letter grid and words to find in the grid, return the location of the first character of the words to be searched

  Student Name: Steven Lee

  Date Created: 4/4/12

  Date Last Modified: 4/6/12

*/

import java.util.*;
import java.io.*;

public class WordSearch 
{
	public static void main (String[]args) throws IOException
	{
		File inFile = new File ("hidden.txt");
		Scanner sc = new Scanner (inFile);
		StringBuffer buff = new StringBuffer();
		
		//create output file if it does not already exist
		File outFile = new File ("found.txt");
		if (outFile.exists())
		{
			System.out.println ("File already exists");
			System.exit(0);
		}
		PrintWriter output = new PrintWriter (outFile);
		
		int m = sc.nextInt(); //rows
		int n = sc.nextInt(); //columns
		char [][] grid = new char [m][n]; //establish the dimensions of the grid
		
		//transfer file grid into array
		for (int i = 0; i < grid.length; i ++)
		{
			for (int j = 0; j < grid[i].length; j++)
			{
				String s = sc.next();
				grid[i][j] = s.charAt(0);
			}
		}
		int runs = sc.nextInt(); //number of word searches
		//loop to initiate search for each word
		for (int i = 0; i < runs; i++)
		{
			int row = 0;
			int col = 0;
			//boolean variable where word is already found
			boolean found = false;
			String word = sc.next();
			int length = word.length();
			//create a reverse word to check
			buff.append(word);
			buff.reverse();
			String backWord = buff.toString();
			
			//check rows
			for (int j = 0; j < grid.length; j++)
			{
				//break out of loop if word is already found
				if (found)
					break;
				
				for (int k = 0; k <= grid[j].length  -length; k++)
				{
					//break out of loop if word is already found
					if (found)
						break;

					int idx = k;
					int count = 0;
					//check for forward occurrences
					while (word.charAt(count++) == (grid[j][idx++]))
					{
						//if all characters are found in the word
						if (count == length)
						{
							row = j+1;
							col = k+1;
							found = true;
							break;
						}
						//end row search if character search reaches end without finding word
						if (idx == grid[0].length)
						{
							break;
						}
					}

					idx = k;
					count = 0;
					if (found)
						break;
					//check for backward occurrences
					while (backWord.charAt(count++) == (grid[j][idx++]))
					{
						if (count == length)
						{
							row = j+1;
							col = k+length;
							found = true;
							break;
						}
						//end row search if character search reaches end without finding word
						if (idx == grid[0].length)
						{
							break;
						}
					}
				}
				
			}
			//check columns
			for (int j = 0; j <= grid[0].length - length; j++)
			{
				//break out of loop if word is already found
				if (found)
				{
					break;
				}
				for (int k = 0; k < grid.length; k++)
				{
					//break out of loop if word is already found
					if (found)
					{
						break;
					}

					int idx = j;
					int count = 0;
					//check for forward occurrences
					while (word.charAt(count++) == (grid[idx++][k]))
					{
						
						if (count == length)
						{
							row = j+1;
							col = k+1;
							found = true;
							break;
						}
						if (idx == grid.length-1)
						{
							break;
						}
					}
					
					idx = j;
					count = 0;
					if (found)
						break;
					//check fo rbackward occurrences
					while (backWord.charAt(count++) == (grid[idx++][k]))
					{
						
						//if all characters are found in the word
						if (count == length)
						{
							row = j+1;
							col = k+length;
							found = true;
							break;
						}
						//end row search if character search reaches beginning without finding word
						if (idx == grid.length-1)
						{
							break;
						}
					}
				}
			}
			//check diagonals
			for (int j = 0; j <= grid[0].length - length; j++)
			{
				//break out of loop if word is already found
				if (found)
				{
					break;
				}
				for (int k = 0; k <= grid.length - length; k++)
				{
					//break out of loop if word is already found
					if (found)
						break;

					int idx1 = j;
					int idx2 = k;
					int count = 0;
					//check for forward diagonal occurrences
					while (word.charAt(count++) == (grid[idx1++][idx2++]))
					{
						//if whole word is found
						if (count == length)
						{
							row = j+1;
							col = k+1;
							found = true;
							break;
						}
						if (idx1 == grid.length-1 || idx2 == grid[0].length-1)
						{
							break;
						}
					}
					
					idx1 = j;
					idx2 = k;
					count = 0;
					if (found)
						break;
					//check for right to left diagonal occurrences
					while (word.charAt(count++) == (grid[idx1++][idx2--]))
					{
						//if whole word is found
						if (count == length)
						{
							row = j+1;
							col = k+1;
							found = true;
							break;
						}
						if (idx1 == grid.length-1 || idx2 == 0)
						{
							break;
						}
					}
					
					idx1 = j;
					idx2 = k;
					count = 0;
					if (found)
						break;
					//check for backwards diagonal occurrences
					while (backWord.charAt(count++) == (grid[idx1++][idx2++]))
					{
						
						if (count == length)
						{
							row = j+length;
							col = k+length;
							found = true;
							break;
						}
						if (idx1 == grid.length-1 || idx2 == grid[0].length-1)
						{
							break;
						}
					}
					
					idx1 = j;
					idx2 = k;
					count = 0;
					if (found)
						break;
					while (backWord.charAt(count++) == (grid[idx1++][idx2--]))
					{
						//if whole word is found
						if (count == length)
						{
							row = j+length;
							col = k-length+2;
							found = true;
							break;
						}
						if (idx1 == grid.length-1 || idx2 == 0)
						{
							break;
						}
					}
				}
			}
			
			//output into file if found
			if (found)
			{
				output.printf( "%-15s %-2d %2d %n", word, row, col);
			}
			else
				output.printf( "%-15s %-2d %2d %n", word, 0, 0);
		}
		output.close();
		sc.close();
	}
}