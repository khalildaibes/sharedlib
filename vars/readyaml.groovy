def get_data_centers(jobname){
  def configVal = readYaml file: "datacenters.yml"
  echo configVal['datacenters'].getClass().toString()
  def datacenters = configVal['datacenters']["${jobname}"]["data-centers"]
  return datacenters
}

