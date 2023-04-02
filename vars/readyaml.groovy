def get_data_centers(jobname){
  def filecontent = libraryResource('datacenters.yml')
  def configVal = readYaml(filecontent)
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
post.setRequestMethod("POST")
post.setDoOutput(true)
post.setRequestProperty("Content-Type", "application/json")
post.setRequestProperty("APIKEY", "24df72c4a77c436a8195e0949fa3868a")
post.getOutputStream().write(body.getBytes("UTF-8"));
post.connect();
def postRC = post.getResponseCode();
println(postRC);
if (postRC.equals(200)) {
    echo(post.getInputStream().getText());
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
