# AreaCalculator

1. First do a gradle build on the given project. Result would be generation of area-calculator.jar in the build directory

2. In the docker toolbox run the following commands
$ docker build -t calculator .
$ nohup docker run -p 5000:8080 calculator
#$ docker-machine ip default
This builds the docker container with the project jar uploaded on the container and exposes port 5000 of the container
for the world

3. To test the endpoint use Postman or any equivalent rest client.
GET http://<host>:8080/area/square?Side=8
GET http://<host>:8080/area/circle/4
POST http://<host>:8080/area/rectangle/
Message body
{
  "Length" : 2,
  "Breadth": 4.9
}
Sample response
{
"shape": "RECTANGLE",
"inputSides":{
"Breadth": 4.9,
"Length": 2
},
"area": 9.8
}

4. Check the nohup logs for any errors.

5. Destroy the container after testing
