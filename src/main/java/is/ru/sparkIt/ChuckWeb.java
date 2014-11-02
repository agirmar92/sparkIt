package is.ru.sparkit;

import spark.*;
import static spark.Spark.*;
import spark.servlet.SparkApplication;

public class ChuckWeb implements SparkApplication {
    public static void main(String[] args){
        staticFileLocation("/public");
        SparkApplication chuckweb = new ChuckWeb();
        String port = System.getenv("PORT");
        setPort(4572);
        if (port != null) {
            setPort(Integer.valueOf(port));
        }
        chuckweb.init(); 
    }

    public void init(){
        final ChuckJoke chuckjoke = new ChuckJoke();
        
        post(new Route("/random"){
            @Override
            public Object handle(Request request, Response response){
                String joke = chuckjoke.getRandom();
                return joke;
            }
        });

        post(new Route("/id"){
            @Override
            public Object handle(Request request, Response response){
                Integer number = Integer.valueOf(request.queryParams("id"));
                String joke = chuckjoke.getSpecific(number);
                return joke;
            }
        });
    }
}
