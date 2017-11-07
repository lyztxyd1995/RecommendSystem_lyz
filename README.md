# RecommendSystem_lyz
index.html: main html page

scripts: JavaScript for index.html(use Jquery and Ajax to simplify the web connection)

style： css style for index.html

WEB-INF-class：the backend system

db：The program for database. There are two versions (MongoDb/mysql), could use DBConnectionFactory to switch the database

algorithm：Recommendation algorithm, first search items according to the categories of user's favorite items, then sort by the distance. 

entity：creat an Item class to filter the information that get from the external api.

external: Call api, we use TickedMaster API in this project. Could change API by simply use ExternalApiFactory

rpc：WebServlet programe， use Restful API 
                   
                   SearchItem: search nearby items by calling API
                   
                   RecommendItem：return recommendation items.
                   
                   ItemHistory:  return the history of user's favorite items.
