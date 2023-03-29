def get_data_centers(jobname){
  def configVal = readYaml file: "datacenters.yml"
  if(configVal['datacenters'].containsKey("${jobname}")){
  def datacenters = configVal['datacenters']["${jobname}"]["data-centers"]
  }
  else {
    return [""]
  }
  return datacenters
}

