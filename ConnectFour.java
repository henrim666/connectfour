package connect4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConnectFour {
    final int boardWidth       =8;
    final int boardHeight      =8;
          int totalMovesPlayed;
          int[][] board;
    
    public ConnectFour(){
        board=new int[boardHeight][boardWidth];
        totalMovesPlayed=0;
    }
    
    public static void main(String args[])throws IOException{
        
        ConnectFour c4=new ConnectFour();
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("__Sample Run__");
        System.out.println(" ");
        System.out.println("````text");
        c4.printBoard();
        outer:
        while(true){
            int column=1;
            //PLAYER RED.
            while(true){
            System.out.print("\nPlayer 1 [RED] - choose column (1-7): ");
            try{
            column=Integer.parseInt(br.readLine());
            if (column > 0 && column < 8){
                if(c4.isPlayable(column)){
                    if(c4.playMove(column, 1)){
                        c4.printBoard();
                        System.out.println("\nPlayer 1 [RED] wins!");
                        System.out.println("....");
                        break outer;
                    }
                    break;
                }
                else
                    System.out.println("Error Column "+column+" full");
               }
            else
            	System.out.println("Column entered is not between 1-7 try again");
            } catch (NumberFormatException e) {
                System.out.println("Not a valid input, please try again.");
            	System.out.println("Column entered is not between 1-7 try again");
            }
            }
            c4.printBoard();
            
            //PLAYER GREEN.    
            while(true){
            System.out.print("\nPlayer 2 [GREEN] - choose column (1-7): ");
            try{
            column=Integer.parseInt(br.readLine());
            if (column > 0 && column < 8){
            	if(c4.isPlayable(column)){
                    if(c4.playMove(column, 2)){
                        c4.printBoard();
                        System.out.println("\nPlayer 2 [GREEN] wins!");
                        System.out.println("....");
                        break outer;
                    }
                    break;
                }
                else
                    System.out.println("Error Column "+column+" full");
            }
           	else
               	System.out.println("Column entered is not between 1-7 try again");
            }catch (NumberFormatException e) {
                System.out.println("Not a valid input, please try again.");
            	System.out.println("Column entered is not between 1-7 try again");
            }
            }
            c4.printBoard();
            
            if(c4.isFull()){
                System.out.print("No winner game finished in draw");
                break;
            }
        }
    }
                
    public void printBoard(){
        for(int i=1;i<board.length;i++){
            for(int j=1;j<board[1].length;j++){
                if(board[i][j] == 0)
                    System.out.print("| ");
                else
                	if (board[i][j]==1){
                		System.out.print("|R");
                	}
                	if (board[i][j]==2){
                		System.out.print("|G");
                	}
            }
            System.out.print("|");
            System.out.println();
        }
    }
    
    public boolean playMove(int column, int playerNum){
        int i=1;
        for(i=1;i<boardHeight;i++){
            if(board[i][column] == 1 || board[i][column] == 2){
                board[i-1][column]=playerNum;
                break;
            }
        }
        if(i == boardHeight)
            board[i-1][column]=playerNum;
        
        totalMovesPlayed++;
        return isConnected(i-1,column);
    }
    
    public boolean isPlayable(int column){
        return board[0][column] == 0; 
    }
    
    public boolean isFull(){
        return totalMovesPlayed == boardHeight*boardWidth;
    }
    
    public boolean isConnected(int x, int y){
        int num=board[x][y];
        int count=0;
        int i=y;
        
        //HORIZONTAL.
        while(i<boardWidth && board[x][i] == num){
            count++;
            i++;
        }
        i=y-1;
        while(i>=0 && board[x][i] == num){
            count++;
            i--;
        }
        if(count == 4)
            return true;
        
        //VERTICAL.
        count=0;
        int j=x;
        while(j<boardHeight && board[j][y] == num){
            count++;
            j++;
        }
        if(count == 4)
            return true;
        
        //SECONDARY DIAGONAL.
        count=0;
        i=x;
        j=y;
        while(i<boardWidth && j<boardHeight && board[i][j] == num){
            count++;
            i++;
            j++;
        }
        i=x-1;
        j=y-1;
        while(i>=0 && j>=0 && board[i][j] == num){
            count++;
            i--;
            j--;
        }
        if(count == 4)
            return true;
        
        //LEADING DIAGONAL.
        count=0;
        i=x;
        j=y;
        while(i<boardWidth && j>=0 && board[i][j] == num){
            count++;
            i++;
            j--;
        }
        i=x-1;
        j=y+1;
        while(i>=0 && j<boardHeight && board[i][j] == num){
            count++;
            i--;
            j++;
        }
        if(count == 4)
            return true;
        
        return false;
}
}
