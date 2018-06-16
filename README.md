![sellustrate](https://github.com/jakebildy/sellustrate/blob/master/sellustrate_logo.png?raw=true)

Sellustrate uses image recognition linked to the eBay API to identify items and generate an entire eBay listing, including the description, from a single photo. Originally developed in 36 hours for the Caltech hackathon (HackTech).


# Technical Details 
 **:iphone: Frontend**: Java (Android Studio)

 **:lock: Backend**: Backend hosting is done through Microsoft Azure via Python, and the eBay listing is generated using JavaScript and previewed using a WebView
 
 # How it works :rocket:
The user only needs to take a picture of the item they wish to sell - the app does nearly all the rest!
First, it sorts the image into one of eBay's merchandise categories, using image recognition with Microsoft Azure.
Then, the user is prompted to select the quality of the item - this is done via a simplistic scrolling UI.

Finally, the app uses keywords generated from image recognition and the quality to pull up descriptions and prices on eBay of similar items, and uses an algorithm we created alongside the eBay API to generate a custom listing for what you want to sell.


