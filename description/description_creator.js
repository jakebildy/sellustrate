//Jake Bildy, 2018


function getResponse(url)
{
    var XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", url, false);
    xmlHttp.send(null);
    return xmlHttp.responseText;
}

console.log(getResponse("http://sellustrate.azurewebsites.net"));