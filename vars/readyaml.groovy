def get_data_centers(jobname){
  def configVal = readYaml file: "datacenters.yml"
  echo configVal['datacenters'].toString()
  echo jobname
  if (configVal['datacenters']["${jobname}"]){
    def datacenters = configVal['datacenters']["${jobname}"]["data-centers"]
  }else{
    echo "Job isn't supported"
    return [""]
  }
  return datacenters
}

