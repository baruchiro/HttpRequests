function GET(requestURL)
{
    var request = new XMLHttpRequest();
    request.open("GET",requestURL,true);
    request.onreadystatechange = function(){
        if(request.readyState == 4){
            document.write(request.responseText + "<br>");
        }
    }
    request.send(null);
}

function POST(requestURL, data)
{
    var request = new XMLHttpRequest();
    request.open("POST",requestURL,true);
    request.onreadystatechange = function(){
        if(request.readyState == 4){
            document.write(request.responseText + "<br>");
        }
    }
    request.send("data=" + data);
}

window.onload = function(){
    document.write("GET:https://httpbin.org/ip<br>");
    GET("https://httpbin.org/ip");

    document.write("POST:https://httpbin.org/post    DATA:\"Hello World\"<br>");
    POST("https://httpbin.org/post", "Hello World");
}