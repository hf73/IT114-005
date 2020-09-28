import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class NumberGuesserHW {
	private int level = 1;
	private int strikes = 0;
	private int maxStrikes = 5;
	private int number = 0;
	private boolean isRunning = false;
	final String saveFile = "numberGuesserSave.txt";
   private int randNum = 0;
   private int guess = 0;

	/***
	 * Gets a random number between 1 and level.
	 * 
	 * @param level (level to use as upper bounds)
	 * @return number between bounds
	 */
	public static int getNumber(int level) {
		int range = 9 + ((level - 1) * 5);
		System.out.println("I picked a random number between 1-" + (range + 1) + ", let's see if you can guess.");
		return new Random().nextInt(range) + 1;
   }
  
	private void win() {
		System.out.println("That's right!");
		level++;// level up!
		saveLevel();
		strikes = 0;
		System.out.println("Welcome to level " + level);
		number = getNumber(level);
	}

	private void lose() {
		System.out.println("Uh oh, looks like you need to get some more practice.");
		System.out.println("The correct number was " + number);
		strikes = 0;
		level--;
		if (level < 1) {
			level = 1;
		}
		saveLevel();
		number = getNumber(level);
	}

	private void processCommands(String message) {
		if (message.equalsIgnoreCase("quit")) {
			System.out.println("Tired of playing? No problem, see you next time.");
			isRunning = false;
		}
	}

	private void processGuess(int guess) {
		if (guess < 0) {
			return;
		}
		System.out.println("You guessed " + guess);
		if (guess == number) {
			win();
		} else {
			System.out.println("That's wrong");
			strikes++;
			if (strikes >= maxStrikes) {
				lose();
			} else {
				int remainder = maxStrikes - strikes;
				System.out.println("You have " + remainder + "/" + maxStrikes + " attempts remaining");
				if (guess > number) {
					System.out.println("Lower");
				} else if (guess < number) {
					System.out.println("Higher");
				}
			}
		}
	}
   

	private int getGuess(String message) {
		int guess = -1;
		try {
			guess = Integer.parseInt(message);
		} catch (NumberFormatException e) {
			System.out.println("You didn't enter a number, please try again");

		}
		return guess;
	}

	private void saveLevel() {
		try (FileWriter fw = new FileWriter(saveFile)) {
			fw.write("" + level);// here we need to convert it to a String to record correctly
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean loadLevel() {
		File file = new File(saveFile);
		if (!file.exists()) {
			return false;
		}
		try (Scanner reader = new Scanner(file)) {
			while (reader.hasNextLine()) {
				int _level = reader.nextInt();
				if (_level > 1) {
					level = _level;
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e2) {
			e2.printStackTrace();
			return false;
		}
		return level > 1;
	}
        //saving strikes
   private void saveStrikes(){
      try (FileWriter writer = new FileWriter(saveFile)){
         writer.write(" " + strikes);
         } catch (IOException e){
            e.printStackTrace();
   }
   }
   
   
   //loading strikes
   private boolean loadStrikes(){
      File strike = new File(saveFile);
         if(!strike.exists()){
            return false;
         }
      try (Scanner reader = new Scanner(strike)) {
         while (reader.hasNextLine()) {
            int _strikes = reader.nextInt();
            if( _strikes > 1) {
               strikes = _strikes;
               break;
          }
        }
        }catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;       
        }catch (Exception e2) {
			e2.printStackTrace();
			return false;
        }
         return strikes > 1;
        }
        
        
     //saving number generated
   private void saveNum(){
      try(FileWriter writer = new FileWriter(saveFile)){
      writer.write(" " + number);
         } catch (IOException e){
            e.printStackTrace();
   }
   }
   
   
   //loading number generated
   private boolean loadNum(){
      File rand = new File(saveFile);
         if(!rand.exists()){
            return false;
          }
            try (Scanner reader = new Scanner(rand)) {
         while (reader.hasNextLine()) {
            int _rand = reader.nextInt();
            if(_rand >= 0) {
               number = _rand;
               break;
          }
        }
          }catch (FileNotFoundException e) {  //catching errors
			e.printStackTrace();       
			return false;
		} catch (Exception e2) {
			e2.printStackTrace();
			return false;
		}
   return strikes > 0;
   }
   
   //saving last guess
   private void saveLastGuess(){
      int lastGuess = guess;
      try(FileWriter writer = new FileWriter(saveFile)){
         writer.write("" + guess);
         }catch (IOException e){
            e.printStackTrace();
        }
        }
   //loading last guess
   private boolean loadGuess(){
      File guessFile = new File(saveFile);
      if (!guessFile.exists()){
         return false;
    }
      try (Scanner reader = new Scanner(guessFile)) {
         while (reader.hasNextLine()) {
            int _guess = reader.nextInt();
            if(_guess >= 0) {
               guess = _guess;
               break;
          }
        }
          }catch (FileNotFoundException e) {  //catching errors
			e.printStackTrace();       
			return false;
		} catch (Exception e2) {
			e2.printStackTrace();
			return false;
		}
   return guess > 0;
   }
     


	void run() {
		try (Scanner input = new Scanner(System.in);) {
			System.out.println("Welcome to Number Guesser 4.0!");
			System.out.println("I'll ask you to guess a number between a range, and you'll have " + maxStrikes
					+ " attempts to guess.");
			if (loadLevel()) {
				System.out.println("Successfully loaded level " + level + " let's continue then");
			}
         if (loadNum()){
            System.out.println("Successfully loaded previous number.");
         
        }
        if (loadStrikes()){
            System.out.println("You have: " + strikes + " strikes.");
            }
        
        if (loadGuess()){
            System.out.println("Your last guess was: " + guess);
            }

           
			number = getNumber(level);
			isRunning = true;
			while (input.hasNext()) {
				String message = input.nextLine();
				processCommands(message);
				if (!isRunning) {
					break;
				}
				int guess = getGuess(message);
				processGuess(guess);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		NumberGuesserPart4 guesser = new NumberGuesserPart4();
		guesser.run();
	}
 
}
