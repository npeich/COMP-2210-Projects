 
   //***make second 2d array full of falses to be able to track
   //***if ive been there before
   
   //isonboard
      //start w this
      //returns list of integers 
         //ints are the row/column
          
      List<Integer> isOnBoard(String wordToCheck) { //if not there return empty list
         for each position on the board {
            attempt to construct wordtocheck*** (depth first)
            exit when/if found
      }}
         
         *** boolean dfsOverword(int i (row), int j(column), String wordtocheck,
                                       StringBuilder wordsofar, List<Integer> path)  {
                                       use as perameters bc you'll use recursion
          1 can i stop? (is i/j a valid position?)
            can i stop? (word so far isnt prefix to what im looking for)
            ^ know its a valid position and so far matches the target word
          2 add ints to path, add to wordsofar, havent been here before
            if it matches word, stop
            search thru all the neighbors (recursivly)
          3                             
                                       
         }
         
         
   //getallvalidwords
      //
   
   
   
   
   
   
   public List<Integer> isOnBoard(String wordToCheck) {
        if(wordToCheck.equals(null)) {
            throw new IllegalArgumentException();
        }
        if(!lexiconCalled) {
            throw new IllegalStateException();
        }
        boolean[][] visited = new boolean[squareSize][squareSize];
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int a = 0; a < squareSize; a++) {
            for(int b = 0; b < squareSize; b++) {
                wordOnBoard(a,b,"",wordToCheck,visited,list);
            }
        }
        //wordOnBoard(0,0,"",wordToCheck,visited,list);

        return list;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
     private void wordOnBoard(int i, int x, String str, String found, boolean[][] vis, ArrayList<Integer> word) {
        vis[i][x] = true;
        str = str + board[i][x];
        if(str.equals(found)) {
            for(int j = 0; j < squareSize; j++) {
                for(int k = 0; k < squareSize; k++) {
                    if(vis[j][k] == true) {
                        word.add(j * squareSize + k);
                    }
                }
            }
        }
        if(found.startsWith(str)) {
            for (int row = i-1; row <= i+1 && row < squareSize; row++) {
                for (int col = x-1; col <= x+1 && col < squareSize; col++) {
                    if (row >= 0 && col >= 0 && !vis[row][col]) {
                        wordOnBoard(row, col, str, found, vis, word);
                    }
                }
            }
        }
        // Erase current character from string and mark visited
        // of current cell as false
        str = str.substring(0, str.length()-1);
        vis[i][x] = false;
        
    }
    
    
    
    
    
    @Override
    public SortedSet<String> getAllValidWords(int minimumWordLength) {
   
        if(!lexiconCalled) {
            throw new IllegalStateException();
        }
        if(minimumWordLength < 1) {
            throw new IllegalArgumentException();
        }
        boolean[][] visited = new boolean[squareSize][squareSize];
        TreeSet<String> words = new TreeSet<String>();
        for(int a = 0; a < squareSize; a++) {
            for(int b = 0; b < squareSize; b++) {
                getWords(minimumWordLength,a,b,"",visited,words);
            }
        }


        return words;
    }