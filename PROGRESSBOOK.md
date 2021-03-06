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

## 14 January ~14:00

Today I wanted to delve deeper into my Parse implementations. I started off trying to finish my edit profile screen implementation. 
This turned out to be fairly straightforward (thanks Parse!). Since the user profiles are currently only made up of two edit texts and a user's name, I only needed to 'put' strings into the user ParseUser objects.
To do this a screen first needs to check who is logged in (one simpel line of code with parse).
Then it checks if a user already has a profile filled in, and puts the relevant strings into the edit text boxes.
Then users can edit the texts, and press the save button when ready. This will automatically update the values of the ParseUser's relevant key-value pairs.
Now to finish my alpha I must be implement the browse random profiles function. This is proving to be a bit of a hurdle since Parse doesn't seem to have a random query method.
Therefore, I will either need to make a loop that builds a list of all ParseUsers, and then choose a random entry in that list.
Or I will need to make an id generator (every ParseUser has a randomly generated 10 character ID) that can randomize the proces of choosing a user to display.
Alternatively, I could add my own ParseUserID key-value pair to each ParseUser, allowing me to make this proces a lot easier.

## 15 January ~14:45

I planned to finish implementing my browse profiles functionality, unfortunatily I encountered a bug whilst showing my app to friends last night.
This bug turned out to be pretty hard to figure out since the bug did not involve any usefull logcat errors. It seemed to be happening 'behind the screens'.
It happened whenever I tried logging in or registering a new user. Therefore it was threatening to ruin my alpha since I couldn't enter the app, and couldn't revert back to an old version (since the code hadn't changed in that specific activity since the occurence of the bug).
After a lot of thinking and googling I managed to figure out the problem. 
Apparently Parse doesn't auto-log off when a new version of the app is started from Android Studio, which resulted in my Parse thinking a user was still logged in, even though the app was in the 'logged-off' part of the app.
Therefore I was able to fix the problem by adding a 'log-off' function at start up, which will fix the issue untill I implement an auto-log-on feature for frequent users.
Unfortunately all of this means that I have not been able to spend my day implementing my browse feature. However, I am confident I get this done in time for beta launch.

## 18 January ~17:00

Today was a good day. I pretty much implemented the entire profile searching functionality, except for all the relevant filters (for example, duplicate profiles, or your own profile, can still pop up).
It took me a while to get it working, since it didn't always seem to be storing everything correctly. Eventually it turned out to be two things:
	
	- The first problem was that I did not call saveInBackground() on the parse user at the edit profile screen. This resulted in the changes only being stored locally, meaning that an app restart would clear the data.
	
	- The second problem was that there was a nasty little typo somewhere in some of the getString call methods ("profileText " instead of "profileText"). Must've started in one, and copy pasted into some others.

But no worries, the biggest hurdle has been taken (the random query. Certainly took me some time to figure it out). Tomorrow it's time for the chat function!

## 19 January ~17:00

After a pretty frustrating bugfixing patch I managed to end the day on a positive note. My match function is largely implemented, but might still encounter some bugs.
For everyone like or decline, a integer is added ot the user object with the key being the objectId of the displayed user. 1 represents a like and 0 represents a dislike.
After liking a profile, a check is done to see whether the displayed user had liked the user as well, and if this is the case, a match will be made.
To be able to keep track of matches, new users will be initialized with an empty list under the key "matches". When matched, both users will have the objectId of their match added to the list.
This list will make intializing the match screen relatively easy. I will be able to travel through the list, adding a name to the screen per match in the list.
Hopefully I'll be able to get this done relatively easily tomorrow, so that I can start on the actual chat screen.

## 21 January ~11:15

Today I started working on the match screen, and immediatly made some quick progress with some help of a classmate (Niek). He advised me to use a ListView.
With some of his guidance, and the a stackoverflow thread (http://stackoverflow.com/questions/14773264/convert-listobject-to-string-in-java) I was able to fairly quickly get my listview up and running.

## 21 January ~17:00

It took the rest of the day tweaking to get it to fully work, but it's up and running now.
For example, getting the list to display objectId's was easy, since I had those in a list, at hand.
However, to extract the username of each user, using their objectId, turned out to be really hard, and cracked my brain for quite a while.
Now it's time to make all the list entries clickable. Shortclick should open chat window, longclick should spawn a delete prompt.
If that turns out to be too difficult to implement I'll just make two buttons, like in the design doc.

## 25 January ~19:00

Making progress with youtube embedding. I've started using the YouTube Player API, and have nearly got it to work.
I reckon I'll be able to complete this tomorrow, after which I can start the finishing touches on my app.
I think I'll have to drop the chat function from my MVP for now, and instead add a 'phone number' edit text as part of the registration.
This, after a succesfull match, users can simply be displayed eachothers telephone numbers, allowing them to contact each other outside of the app.

## 27 January ~17:00

Today I realised that trying to implement the YouTubePlayer at this late stage was not a good idea.
I'm going to spend the rest of the day to restore the app to it's previous state, and will continue working on the completion of this course tomorrow.
I'll also alter my MVP criteria.

## 28 January ~11:00

Demotivated by the failure to implement the YouTubePlayerFragment, I have started working towards a fully working, but downscaled MVP.
These finishing touches include the following:

	- A filter in the BrowseScreen, so that people cannot be matched with themselves. 

	- An extra edittext in the register profile so that people have to add their phone numbers (to mitigate the missing chat screen).

	- A checker to see if the youtube link added in the EditProfileScreen is actually a clickable youtube link.

	- Maybe, if I get to it today, the possibility to click a user in the matchesscreen to see their profile, or delete them from the match list.

I got my work cut out for me today... Here it goes.

## 28 January ~12:00

Lots of progress in one hour, however, SignUpInBackGround in REgisterActivity is throwing an exception, and I'm not sure why, so gonna just commit this progress so I can compare code.

## 28 January ~20:30

Decently happy with the end result. Yes, I've had to downscale my MVP, but I've come up with reasonably functional alternatives.
The functionalites that I had planned to implement but have had to drop were the YouTube fragment and the chat function. These proved slightly to challenging to implement under time pressure.
Given more time I am confident that this minor has given me the tools to be able to implement them myself. In fact, I plan to keep on working on this app after the completion of this course.
Anyways, today I managed to add the filter to the browse screen, add phone numbers to the match screen (in a string, not yet clickable unfortunately), a youtubelink checker, and a bunch of other polish level functions.
This checker check to see if the link string contains the string "youtu". This is not foolproof of course, but will at least make it harder for jokers to put weird things there.
All in all decently happy, the app looks reasnably well, but I could have done more with more time.