# FirstGameRepo
 
This game was originally meant to be a platformer which was inspired by the tutorial for libGDX which created a Break Out! style game 

As of writing this it doesn't accomplish that end goal, and I'm not currently planning to work on it for the foreseeable future

Instead, this "game" really just became an incredibly simple physics/collision engine

The game can be played with: Left arrow key to move left, right arrow key to move right, up arrow key to jump

## Takeaways
The big takeaway for this game was that physics engines, even simple ones, are pretty hard to get correct. Another takeaway is that if you have multiple systems running at once, in this case a physics system and a jumping system, you need to make sure they work correctly together, and in my opinion it is best to develop them to work together from the beginning rather than slap one on part way through developing the other. As is, I wouldn't be surprised if the two still "fight" a little, but it is working according to the eyes so the user wouldn't know.


## As for the physics engine
I initially started with a bunch of if statements to "push" the player chararcter, called "Dude" in the code, around as necessary. However, this, along with the jumping in the game, caused issues with him phasing through objects, getting pushed under objects he shouldn't have been pushed under, and issues with not falling when he was supposed to. I went through a few steps to try and fix this, mostly by consolidating if statements into much larger checks. While this worked, it was very complex and it looked ugly. In the end, I decided to use a switch satement to move the Dude around. This switch statement worked because I added some basic functionality that made it so when the Dude was within the bounds of a platform, some measurements were taken as to how far the respective edge of the dude was from each edge. Then, whichever of those measurements were the least decides which direction the dude is pushed. This worked very well, and once it was done I was happy with the physics engine.

## Possible future work
After the physics engine was done I messed with producing platforms that weren't just the staircase I had been using for testing. This is still very rudimentary and does not work well, and if I were to return to the project this is one of the first things that I would need to work on.

Ensuring the jumping and platform systems 100% don't "fight" would also be a high priority.

Making it so the user can go to different stages by moving off screen, with some functionality to have a permanent "bottom level of stages", and ensuring that if they moved back to an already created stage it would be the same as they left it. So effectively a mapping system.
