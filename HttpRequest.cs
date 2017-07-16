using System;
using System.IO;
using System.Net;
using System.Threading.Tasks;

namespace HttpRequest
{
    class Program
    {
        public static  string ExecuteRequest(HttpWebRequest httpRequest)
        {
            HttpWebResponse response = (HttpWebResponse)httpRequest.GetResponseAsync().Result;  //this is Task<>
            StreamReader streamReader = new StreamReader(response.GetResponseStream());
            return streamReader.ReadToEnd();
        }

        public static  string GET(string url)
        {
            HttpWebRequest request = WebRequest.CreateHttp(url);
            return  ExecuteRequest(request);
        }

        public static string POST(string url, string payload)
        {
            payload = "data=" + payload;
            HttpWebRequest request = WebRequest.CreateHttp(url);
            request.Method = "POST";
            request.ContentType  = "application/x-www-form-urlencoded";


            StreamWriter streamWriter = new StreamWriter(request.GetRequestStreamAsync().Result);
            streamWriter.Write(payload);
            streamWriter.Flush();

            return ExecuteRequest(request);
        }

        static void Main(string[] args)
        {
            Console.WriteLine("GET:https://httpbin.org/ip");
            Console.WriteLine(GET("https://httpbin.org/ip"));

            Console.WriteLine("POST:https://httpbin.org/post\tDATA:\"Hello World\"");
            Console.WriteLine(POST("https://httpbin.org/post", "Hello World"));

            Console.Read();
        }
    }
}