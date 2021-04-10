## Prerequisites 
- Java
- maven 
	To install maven:
	- Install brew : ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
	- Install maven : brew install maven
- mongo
	To install Mongo:
	- brew tap mongodb/brew
	- brew install mongodb-community
	- start mongodb through brew services start mongodb
	- Access shell using mongo 
- postman to test the APIs

## Tools
- Eclipse or IntelliJ IDEA (or any preferred IDE) with embedded Gradle
- Maven (version >= 3.6.0)
- Postman (or any RESTful API testing tool)

###  Build and Run application
1. cd <absolute-path-to-directory>/RBCAssessment
2. In terminal, mvn clean install (it will build application and create **jar** file under target directory)
3. Run jar file from below path with given command
	java -jar ~/<path to RBCAssessment>/target/rbcassessment-0.0.1-SNAPSHOT.jar
	
Notes to test:

1. POST request to Upload stocks - http://localhost:8080/stocks/uploadStocks
	Headers:= Accept:application/json, Origin:http://localhost:8080, Content-Type:multipart/form-data
	Body:= Key-> file Value -> dow_jones_index.csv

2. GET request by stockId - http://localhost:8080/stocks/getStocks/<stockid>
	e.g : http://localhost:8080/stocks/getStocks/AA

3. POST request to add a new stock record:
 	- http://localhost:8080/stocks/insertStock
 	Request Body:
 	{
  		"quarter": "11",
  		"stockId": "CAT",
  		"date": "3/25/2021",
  		"open": "$13.85",
  		"high": "$14.69",
  		"low": "$13.80",
  		"close": "$14.25",
  		"volume": "1453438639",
  		"percent_change_price": "2.88809",
  		"percent_change_volume_over_last_wk": "-47.64583288",
  		"previous_weeks_volume": "1453438639",
  		"next_weeks_open": "$15.08",
  		"next_weeks_close": "$14.25",
  		"percent_change_next_weeks_price": "-5.50398",
  		"days_to_next_dividend": "47",
  		"percent_return_next_dividend": "0.0655738"
	}

 	

