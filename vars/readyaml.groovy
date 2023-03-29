def get_data_centers(jobname){
  def configVal = readYaml file: "datacenters.yml"
  def datacenters= [""]
  try{

      datacenters = configVal['datacenters']["${jobname}"]["data-centers"]
    }
    }catch(Exception e){

        echo 'Job dosent support datacenters'
    return [""]
    }
  return datacenters
}

