@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7.1')

@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7')
@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7' )
import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import groovy.json.JsonBuilder
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
  def configVal = readYaml  text: filecontent
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


@NonCPS
def cops_api1(authToken,customer) {
def createUrl = new URL('http://cops.onbmc.com/cops/api.php')

def map = [:]
//create JSON
def jsonBody = """{
    "jsonrpc": "2.0",
    "method": "getprojectparams",
    "params": {
        "project": "refash1np02",
        "filter": "/DB_TYPE/"
    },
    "id": "getprojectparams"
}"""
def client = new RESTClient(createUrl)
client.headers['Content-Type'] = 'application/json'
client.headers['APIKEY'] = '24df72c4a77c436a8195e0949fa3868a'
client.headers['Cookie'] = 'cops=cr6i8nrmhk8slimnb6279gm5q1'
def resp = client.post(body : jsonBody, contentType: JSON )
echo resp.data 
return resp.data 
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
