def get_data_centers(jobname){
  def configVal = readYaml file: "datacenters.yml"
  def datacenters = configVal['datacenters']["${jobname}"]["data-centers"] ?: echo "Job isn't supported"
  return datacenters
}

