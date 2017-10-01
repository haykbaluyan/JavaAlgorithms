/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordpuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author haykbaluyan
 */

class WordSearch
{
    char[][] theBoard;
    int rows;
    int columns;
    
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader puzzleStream;
    BufferedReader wordsStream;
    String[] theWords;
    
    public WordSearch() throws IOException
    {
        puzzleStream = openFile("Enter puzzle file");
        wordsStream = openFile("Enter words file");
        System.out.println("Reading files...");
        readPuzzle();
        readWords();
        
    }
    
    private BufferedReader openFile(String message)
    {
        System.out.println(message);
        String fileName = "";
        FileReader theFile;
        BufferedReader fileIn;
        fileIn = null;
        try
        {
            fileName = in.readLine();
            if(fileName == null)
                System.exit(0);
            theFile = new FileReader(fileName);
            fileIn = new BufferedReader(theFile);
        }
        catch(IOException e)
        {
            System.err.println("Cannot read file with name " + fileName);
        }
        if(fileIn == null)
            System.exit(0);
        
        System.out.println("Opened " + fileName);
        return fileIn;
    }
    
    private void readPuzzle() throws IOException
    {
        List<String> puzzleLines = new ArrayList<>();
        String oneLine;
        if((oneLine = puzzleStream.readLine()) == null)
            throw new IOException("No lines in puzzle file.");
        columns = oneLine.length();
        puzzleLines.add(oneLine);
        while((oneLine = puzzleStream.readLine()) != null)
        {
            if(oneLine.length() != columns)
            {
                System.out.println("Puzzle is not rectangular. Skipping row.");
            }
            else
            {
                puzzleLines.add(oneLine);
            }
        }
        
        rows = puzzleLines.size();
        
        theBoard = new char[rows][columns];
        int r = 0;
        for(String theLine : puzzleLines)
        {
            theBoard[r++] = theLine.toCharArray();
        }
    }
    
    private void readWords() throws IOException
    {
        List<String> words;
        words = new ArrayList<>();
        String thisWord;
        while((thisWord = wordsStream.readLine()) != null)
        {
            words.add(thisWord);
        }
        
        words.sort(new Comparator<String>() {
            @Override
            public int compare(String i1,String i2)
            {
                return i1.toLowerCase().compareTo(i2.toLowerCase());
            }
        });
        theWords = new String[words.size()];
        theWords = words.toArray(theWords);
    }
    
    public String[] getTheWords()
    {
        return theWords;
    }
    
    public char[][] getTheBoard()
    {
        return theBoard;
    }
    
    public void solvePuzzle()
    {
        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < columns; c++)
            {
                for(int rd = -1; rd <= 1; rd++)
                {
                    for(int cd = -1; cd <= 1; cd++)
                    {
                        if(rd != 0 || cd != 0)
                        {
                            solveDirection(r, c, rd, cd);
                        }
                    }
                }
            }
        }
    }
    
    public int solveDirection(int baseRow, int baseCol, int rowDelta, int colDelta)
    {
        int numMatches = 0;
        String charSequence = "";
        
        charSequence += theBoard[baseRow][baseCol];
        for(int i = baseRow + rowDelta, j = baseCol + colDelta; 
            i >= 0 && j >= 0 && i < rows && j < columns; 
            i += rowDelta, j += colDelta)
        {
            charSequence += theBoard[i][j];
            int searchResult = prefixSearch(theWords, charSequence);
            if(searchResult == theWords.length)
            {
                break;
            }
            
            if(!theWords[searchResult].startsWith(charSequence))
            {
                break;
            }
            
            if(theWords[searchResult].equals(charSequence))
            {
                numMatches++;
                System.out.println( "Found " + charSequence + " at " + baseRow + " " + baseCol + " to " + i + " " + j );
            }
        }
        
        return numMatches;
    }
    
    public int prefixSearch(String[] a, String s)
    {
        int idx = Arrays.binarySearch(a, s);
        if(idx < 0)
            return -idx - 1;
        return idx;
    }
}
public class WordPuzzle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WordSearch wordSearch = null;
        try
        {
            wordSearch = new WordSearch();
            String[] theWords = wordSearch.getTheWords();
            char[][] theBoard = wordSearch.getTheBoard();
            
            for(char[] r : theBoard)
            {
                for(char c : r)
                {
                    System.out.print(c + " ");
                }
                System.out.println();
            }
        }
        catch(IOException e)
        {
            System.out.println("IO error.");
            e.printStackTrace();
            return;
        }
        
        System.out.println( "Solving..." );
        wordSearch.solvePuzzle();
    }
    
}
