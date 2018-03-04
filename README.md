![sellustrate](https://github.com/jakebildy/sellustrate/blob/master/sellustrate_logo.png?raw=true)

Sellustrate uses image recognition linked to the eBay API to create a description to generate eBay item profiles from a single photo. Developed in 36 hours for the Caltech hackathon (HackTech).


# Technical Details
 **Frontend**: Java (Android Studio)

 **Backend**: Python (Microsoft Azure)
 
 # How it works
The user only needs to take a picture of the item they wish to sell, the app does nearly all the rest!
First, it sorts the image into one of eBay's merchandise categories, using image recognition with Microsoft Azure.
Then, the user is prompted to select the quality of the item - this is done via a simplistic scrolling UI.

Finally, the app uses keywords generated from image recognition and the quality to pull up descriptions and prices on eBay of similar items, and uses an algorithm we created alongside the eBay API to generate a custom listing for what you want to sell.


