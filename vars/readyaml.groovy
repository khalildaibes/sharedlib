@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7.1')

import groovyx.net.http.HTTPBuilder
//import groovyx.net.http.ContentType // this doesn't import ContentType
//import groovyx.net.http.Method // this doesn't import Method
import groovyx.net.http.RESTClient
import groovyx.net.http.HttpResponseDecorator

// ContentType static import
import static groovyx.net.http.ContentType.*
// Method static import
import static groovyx.net.http.Method.*

def get_data_centers(jobname){
  def filecontent = libraryResource('datacenters.yml')
  File file = File.createTempFile("temp",".yml")
	RESULT = file.write filecontent
  echo "${RESULT}"
  def configVal = readYaml  file: file.absolutePath
  try{
      datacenters = configVal['datacenters']["${jobname}"]["data-centers"]
    }
    catch(Exception e){
        echo 'Job dosent support datacenters'
        return [""]
    }
  echo 'Job datacenters found'
  return datacenters
}


def get_db_type(customer){
  def db_type = "Null"
	json_result = cops_api1("24df72c4a77c436a8195e0949fa3868a","${customer}")
return json_result  
}


def cops_api1(authToken,customer) {
def body = [
   jsonrpc= "2.0",method= "getprojectparams", params= [project= "refash1np02",filter='/DB_TYPE/'],id= "getprojectparams"
]
def post = new URL("http://cops.onbmc.com/cops/api.php").openConnection();
def url = "http://cops.onbmc.com/cops/api.php"
def requestBody = [jsonrpc= "2.0",method= "getprojectparams", params= [project= "refash1np02",filter="""/DB_TYPE/"""],id= "getprojectparams"]

def http = new HTTPBuilder(url)

http.request(POST, "applicaiton/json") { req ->
  body = requestBody
  response.success = { resp, json ->
    println "Success: ${resp.statusLine}"
    println "Response body: ${json}"
  }
  response.failure = { resp, json ->
    println "Failure: ${resp.statusLine}"
    println "Response body: ${json}"
  }
}
echo result
}

def cops_api(authToken,customer) {
  http.request(POST) {
      uri.path = 'http://cops.onbmc.com/cops/api.php'
      body = [jsonrpc= "2.0",method= "getprojectparams", params= [project= "refash1np02",filter='/DB_TYPE/'],id= "getprojectparams"]

      requestContentType = "applicaiton/json"

      response.success = { resp, json ->
          echo "Success! ${resp.status}"
          echo json.toString()
      }

      response.failure = { resp ->
          println "Request failed with status ${resp.status}"
      }
  }
	return json
}
