@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7')
@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7' )
import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import groovy.json.JsonBuilder

def get_data_centers(jobname){
  def filecontent = libraryResource('datacenters.yml')
  File file = File.createTempFile("temp",".yml")
  file.write filecontent
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
def resp = client.post(body : jsonBody, contentType: JSON )
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
