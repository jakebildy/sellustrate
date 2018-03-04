//Jake Bildy, 2018

//Keyword to JSON object
function key2JSON(keyword)
{
    var obj = {"search" : keyword};
    return obj;
}

function getResponse(url)
{
    var XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", url, false);
    xmlHttp.send(key2JSON("bicycle"));
    return xmlHttp.responseText;
}

console.log(getResponse("http://sellustrate.azurewebsites.net/desc"));