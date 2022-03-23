import model.Difficulty;
//import model.Minesweeper;
import model.Minesweeper;
import model.PlayableMinesweeper;
import view.MinesweeperView;

public class App
{

    public static void main(String[] args) throws Exception
    {
        MinesweeperView view = new MinesweeperView();

        //Uncomment the lines below once your game model code is ready; don't forget to import your game model 
        PlayableMinesweeper model = new Minesweeper();
        view.setGameModel(model);




        /*
         int a = countHelp(i,j);

                        if(a==0)
                        {
                            for (int i2 = i-1; i2 <= i+1; i2++)
                            {
                                for (int j2 = j-1; j2 <= j+1; j2++)
                                {
                                    if(i2>=0 && i2< board[0].length && j2>=0 && j2<board.length)
                                    {
                                        board[j2][i2].isOpened();
                                        board[j2][i2].open();
                                        viewNotifier.notifyOpened(i2,j2,countHelp(i2,j2));
                                        //int b = countHelp(i2,j2);
                                    }

                               }

                           }
                       }
         */


        /**
            Your code to bind your game model to the game user interface
        */
        
        
        //model.startNewGame(Difficulty.EASY);
    }
}
