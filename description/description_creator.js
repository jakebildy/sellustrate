//Jake Bildy, 2018

var request = require('request');

var JSONSearchObject = {"search" : "tshirt"};

var responseJSON;

request({
    url: "http://sellustrate.azurewebsites.net/desc",
    method: "POST",
    json: true,
    body: JSONSearchObject
}, function (error, response, body){
    console.log(response);
});


//descriptions = JSON.parse(getResponse("http://sellustrate.azurewebsites.net/desc"));

//function getWordsFrequency()
//{

//}