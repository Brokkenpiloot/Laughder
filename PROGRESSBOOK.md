# Laughder Progress book

## 12 January ~12:00

After falling ill at the end of the first week I was having some trouble doing everything the neat way, partially because my laptop and Git weren't exactly helping clam my own chaos.
Now however, after having solved all the issues that were holding me back I decided to start my progress book.
Today I have back bone of an app ready. It has all the necessary screens, but very little of the important functionalities are implemented.
For example, there is a register screen, that checks to see whether the password edit text and the confirm password edit text are identical, and allows users to create a profile if 'registering' was 'succesfull'.
However, this activity does not infact create a profile one the parse server yet.
The same goes for the edit profile screen. It shows the user two edit texts that they can fill in, but it doesn't actually link this information to a user account yet.
There lies my challenge for today, and possibly this week, depending on how much work this will cost me.

So, in short, my to-do list for today is to allow users to create an account by registering, and to allow users to log in once their profile has been created.
To do this, I will need to be able to store account information, and to verify that information later on in the log in process.
Once this is possible I hope to be able to get started on making profiles, but let's just see how far I get with this.

## 12 January ~13:30

Good tutorials make all the difference. http://www.androidbegin.com/tutorial/android-parse-com-simple-login-and-signup-tutorial/
After reading this through carefully, and understanding the lines of code I was able to implement my log in and register functionalities fairly easily.
Some lines I could directly copy but there were some subtle differences so it took figuring out, but I got it.
