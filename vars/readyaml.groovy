def get_data_centers(jobname){
  def configVal = readYaml file: "datacenters.yml"
  if (configVal['datacenters'].containsValue("${jobname}")){
    def datacenters = configVal['datacenters']["${jobname}"]["data-centers"]
  }else{
    echo "Job isn't supported"
    return [""]
  }
  return datacenters
}

