import groovyx.net.http.ContentType

def get_data_centers(jobname){
  def configVal = readYaml file: "datacenters.yml"
  def datacenters= [""]
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


def get_db_type(custoemr){
  def db_type = "Null"
  json_result = cops_api(customer)
return db_type  
}

def cops_api(authToken,customer) {
  http.request(POST) {
      uri.path = 'http://cops.onbmc.com/cops/api.php'
      body = [jsonrpc= "2.0",method= "getprojectparams", params= [project= "refash1np02",filter "/DB_TYPE/"],id= "getprojectparams"]

      requestContentType = ContentType.JSON

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
